package com.shifts.api.hours.calculator.shifts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class TimePunch {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date clockedIn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date clockedOut;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date created;
    int hourlyWage;
    long id;
    long locationId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date modified;
    long userId;
}
