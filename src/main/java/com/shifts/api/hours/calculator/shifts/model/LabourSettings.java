package com.shifts.api.hours.calculator.shifts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Alex P. Andrade on 23/06/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabourSettings {
    @JsonIgnore
    boolean autoBreak;
    double dailyOvertimeMultiplier;
    int dailyOvertimeThreshold;
    boolean overtime;
    double weeklyOvertimeMultiplier;
    int weeklyOvertimeThreshold;
    List<AutoBreakRule> autoBreakRules;
}
