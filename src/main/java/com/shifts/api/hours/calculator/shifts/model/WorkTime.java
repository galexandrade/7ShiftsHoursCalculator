package com.shifts.api.hours.calculator.shifts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Alex P. Andrade on 24/06/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkTime {
    Date weekStart;
    Date weekEnd;
    double regularHours;
    double dailyOvertime;
    double weeklyOvertime;
}
