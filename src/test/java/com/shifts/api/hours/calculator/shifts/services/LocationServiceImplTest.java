package com.shifts.api.hours.calculator.shifts.services;

import com.shifts.api.hours.calculator.shifts.model.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex P. Andrade on 23/06/2018.
 */
@SpringBootTest(properties = {"7shifts.api.locations=https://shiftstestapi.firebaseio.com/locations.json"})
public class LocationServiceImplTest {
    private LocationService locationService;

    @Before
    public void setUp() throws Exception {
        locationService = new LocationServiceImpl();
    }

    @Test
    public void loadDataTest() throws IOException {
        List<Location> locations = this.locationService.loadData();
        Assert.assertTrue(locations.size() > 0);
    }

}