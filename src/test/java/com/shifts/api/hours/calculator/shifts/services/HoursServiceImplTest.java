package com.shifts.api.hours.calculator.shifts.services;

import com.shifts.api.hours.calculator.shifts.model.LabourSettings;
import com.shifts.api.hours.calculator.shifts.model.Location;
import com.shifts.api.hours.calculator.shifts.model.User;
import com.shifts.api.hours.calculator.shifts.model.WorkTime;
import com.shifts.api.hours.calculator.shifts.utils.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex P. Andrade on 24/06/2018.
 */
public class HoursServiceImplTest {

    private HoursService hoursService;

    @Before
    public void setUp() throws Exception {
        hoursService = new HoursServiceImpl(new LocationServiceImpl(), new TimePuchServiceImpl());
    }

    @Test
    public void loadDataTest() throws IOException {
        List<User> users = this.hoursService.loadData();
        Assert.assertTrue(users.size() > 0);
    }

    @Test
    public void calculateWorkHours() throws Exception {
        List<WorkTime> workTimes = this.hoursService.calculateWorkHours(517150L);


        Assert.assertEquals(workTimes.size(), 8);
        Assert.assertEquals(DateUtils.getLocalDateTimeFromDate(workTimes.get(0).getWeekStart()).getDayOfMonth(), 11);
        Assert.assertEquals(DateUtils.getLocalDateTimeFromDate(workTimes.get(0).getWeekEnd()).getDayOfMonth(), 17);
    }

    @Test
    public void calculateWeeklyOverwork(){
        //Given
        LabourSettings labourSettings = new LabourSettings();
        labourSettings.setWeeklyOvertimeThreshold(2400);

        Location location = new Location();
        location.setLabourSettings(labourSettings);

        WorkTime workTime = new WorkTime();
        workTime.setRegularHours(3000);

        //When
        WorkTime workTimeCalulated = this.hoursService.calculateWeeklyOverwork(workTime, location);

        //Then
        boolean truthProofWeeklyOvertime = workTimeCalulated.getWeeklyOvertime() == 600;
        boolean truthProofRegularHours = workTimeCalulated.getRegularHours() == 2400;

        Assert.assertTrue(truthProofWeeklyOvertime);
        Assert.assertTrue(truthProofRegularHours);
    }
}