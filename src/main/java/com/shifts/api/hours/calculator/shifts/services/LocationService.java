package com.shifts.api.hours.calculator.shifts.services;

import com.shifts.api.hours.calculator.shifts.model.Location;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex P. Andrade on 24/06/2018.
 */
public interface LocationService {
    public List<Location> loadData() throws IOException;
}
