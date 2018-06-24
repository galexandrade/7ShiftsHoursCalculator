package com.shifts.api.hours.calculator.shifts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Alex P. Andrade on 23/06/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    long id;
    String address;
    String city;
    String country;
    String created;
    double lat;
    double lng;
    String modified;
    String state;
    String timezone;
    LabourSettings labourSettings;
}
