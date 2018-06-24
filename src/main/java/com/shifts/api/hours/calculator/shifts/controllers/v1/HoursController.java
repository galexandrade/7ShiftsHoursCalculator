package com.shifts.api.hours.calculator.shifts.controllers.v1;

import com.shifts.api.hours.calculator.shifts.model.WorkTime;
import com.shifts.api.hours.calculator.shifts.services.HoursService;
import com.shifts.api.hours.calculator.shifts.services.HoursServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex P. Andrade on 23/06/2018.
 */
@RestController
@RequestMapping(HoursController.BASE_URL)
public class HoursController {
    public static final String BASE_URL = "/api/v1/hours";
    private final HoursService hoursService;

    public HoursController(HoursServiceImpl hoursService) {
        this.hoursService = hoursService;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkTime> getUserWorkHours(@PathVariable Long userId) throws IOException {
        return this.hoursService.calculateWorkHours(userId);
    }

}
