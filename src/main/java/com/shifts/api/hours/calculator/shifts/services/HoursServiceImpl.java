package com.shifts.api.hours.calculator.shifts.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shifts.api.hours.calculator.shifts.exceptions.InvalidUserException;
import com.shifts.api.hours.calculator.shifts.exceptions.ResourceNotFoundException;
import com.shifts.api.hours.calculator.shifts.model.Location;
import com.shifts.api.hours.calculator.shifts.model.TimePunch;
import com.shifts.api.hours.calculator.shifts.model.User;
import com.shifts.api.hours.calculator.shifts.model.WorkTime;
import com.shifts.api.hours.calculator.shifts.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Alex P. Andrade on 23/06/2018.
 */
@Service
public class HoursServiceImpl implements HoursService {
    @Value("${7shifts.api.users}")
    String apiUrl = "https://shiftstestapi.firebaseio.com/users.json";

    private final LocationService locationService;
    private final TimePuchService timePuchService;

    public HoursServiceImpl(LocationService locationService, TimePuchService timePuchService) {
        this.locationService = locationService;
        this.timePuchService = timePuchService;
    }

    public List<User> loadData() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(apiUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        List<User> users = new ArrayList<>();
        root.forEach(locationNode -> {
            locationNode.forEach(userNode -> {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    User user = objectMapper.readValue(userNode.toString(), User.class);
                    users.add(user);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });

        return users;
    }

    @Override
    public List<WorkTime> calculateWorkHours(long userId) throws IOException {
        List<User> users = this.loadData();
        List<Location> locations = this.locationService.loadData();
        List<TimePunch> timePunches = this.timePuchService.loadData();

        Optional<User> user = users.parallelStream()
                .filter(userElement -> userElement.getId() == userId)
                .findFirst();

        if(!user.isPresent())
            throw new InvalidUserException();

        List<TimePunch> userTimePunches = timePunches.parallelStream()
                .filter(timePunchElement -> timePunchElement.getUserId() == user.get().getId())
                .sorted(Comparator.comparing(TimePunch::getClockedIn))
                .collect(Collectors.toList());

        if(userTimePunches.size() == 0)
            return null;

        Optional<Location> userLocation =  locations.stream()
                .filter(locationElement -> locationElement.getId() == user.get().getLocationId())
                .findFirst();

        if(!userLocation.isPresent())
            throw new ResourceNotFoundException("User location not found!");

        return performCalc(userTimePunches, userLocation.get());
    }

    /**
     * Perform the calculation
     * @param userTimePunches
     * @param userLocation
     * @return
     */
    private List<WorkTime> performCalc(List<TimePunch> userTimePunches, Location userLocation){
        List<WorkTime> workTimes = new ArrayList<>();

        /**
         * Read the users punch time and calculate daily overwork
         * It saves in a List weekly
        */
        userTimePunches.forEach(timePunch -> {
            LocalDateTime ini = DateUtils.getLocalDateTimeFromDate(timePunch.getClockedIn());
            LocalDateTime end = DateUtils.getLocalDateTimeFromDate(timePunch.getClockedOut());

            Duration duration = Duration.between(ini, end);
            long timeWorked = Math.abs(duration.toMinutes());

            WorkTime savedWorkTime;
            Optional<WorkTime> optionalWorkTime = workTimes.stream()
                    .filter(workTimeElment -> {
                        Calendar calendar = new GregorianCalendar();
                        calendar.setTime(workTimeElment.getWeekStart());
                        int weekNumberWorkTimeIni = calendar.get(Calendar.WEEK_OF_YEAR);

                        calendar.setTime(timePunch.getClockedIn());
                        int weekNumberCurrent = calendar.get(Calendar.WEEK_OF_YEAR);

                        return weekNumberWorkTimeIni == weekNumberCurrent &&
                                workTimeElment.getWeekStart().getYear() == timePunch.getClockedIn().getYear();
                    })
                    .findFirst();

            boolean foundWeekSaved = false;
            if(optionalWorkTime.isPresent()) {
                savedWorkTime = optionalWorkTime.get();
                savedWorkTime.setWeekEnd(timePunch.getClockedOut());
                foundWeekSaved = true;
            }
            else{
                savedWorkTime = new WorkTime();
                savedWorkTime.setWeekStart(timePunch.getClockedIn());
                savedWorkTime.setWeekEnd(timePunch.getClockedOut());
            }

            long regularHours;
            long dailyOvertime = 0;
            if(timeWorked > userLocation.getLabourSettings().getDailyOvertimeThreshold()){
                regularHours = userLocation.getLabourSettings().getDailyOvertimeThreshold();
                dailyOvertime = timeWorked - userLocation.getLabourSettings().getDailyOvertimeThreshold();
            }
            else
                regularHours = timeWorked;

            savedWorkTime.setDailyOvertime(savedWorkTime.getDailyOvertime() + dailyOvertime);
            savedWorkTime.setRegularHours(savedWorkTime.getRegularHours() + regularHours);

            if(!foundWeekSaved){
                workTimes.add(savedWorkTime);
            }
        });

        //After all the work times calculated by week, it will calculate the weekly overwork
        workTimes.parallelStream()
                .map(workTimeElement -> this.calculateWeeklyOverwork(workTimeElement, userLocation));

        return workTimes;
    }

    /**
     * Calculate the Weekly Overwork
     * @param workTime
     * @param location
     * @return
     */
    public WorkTime calculateWeeklyOverwork(WorkTime workTime, Location location){
        long weeklyOvertimeThreshold = location.getLabourSettings().getWeeklyOvertimeThreshold();
        if(workTime.getRegularHours() > weeklyOvertimeThreshold){
            workTime.setWeeklyOvertime(workTime.getRegularHours() - weeklyOvertimeThreshold);
            workTime.setRegularHours(weeklyOvertimeThreshold);
        }

        return workTime;
    }

}
