package com.shifts.api.hours.calculator.shifts.utils;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Alex P. Andrade on 24/06/2018.
 */
public class DateUtils {
    public static LocalDateTime getLocalDateTimeFromDate(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return LocalDateTime.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
    }
}
