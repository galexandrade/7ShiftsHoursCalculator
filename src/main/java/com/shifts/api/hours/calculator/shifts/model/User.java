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
public class User {
    long id;
    String active;
    String created;
    String email;
    String firstName;
    String lastName;
    long locationId;
    String modified;
    String photo;
    long userType;
    double hourlyWage;
}
