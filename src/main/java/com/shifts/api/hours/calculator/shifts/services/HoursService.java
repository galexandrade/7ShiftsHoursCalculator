package com.shifts.api.hours.calculator.shifts.services;

import com.shifts.api.hours.calculator.shifts.model.Location;
import com.shifts.api.hours.calculator.shifts.model.User;
import com.shifts.api.hours.calculator.shifts.model.WorkTime;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex P. Andrade on 24/06/2018.
 */
public interface HoursService {
    List<User> loadData() throws IOException;
    List<WorkTime> calculateWorkHours(long userId) throws IOException;

    WorkTime calculateWeeklyOverwork(WorkTime workTime, Location location);
}
