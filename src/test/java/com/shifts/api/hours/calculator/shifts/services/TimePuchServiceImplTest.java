package com.shifts.api.hours.calculator.shifts.services;

import com.shifts.api.hours.calculator.shifts.model.TimePunch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alex P. Andrade on 24/06/2018.
 */
public class TimePuchServiceImplTest {
    private TimePuchService timePuchService;
    @Before
    public void setUp() throws Exception {
        this.timePuchService = new TimePuchServiceImpl();
    }

    @Test
    public void loadData() throws Exception {
        List<TimePunch> timePunches = this.timePuchService.loadData();
        Assert.assertTrue(timePunches.size() > 0);
    }

}