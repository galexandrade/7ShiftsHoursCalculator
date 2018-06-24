package com.shifts.api.hours.calculator.shifts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Alex P. Andrade on 23/06/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoBreakRule {
    int breakLength;
    int threshold;
}
