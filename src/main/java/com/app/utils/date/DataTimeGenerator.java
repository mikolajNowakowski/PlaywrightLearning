package com.app.utils.date;

import com.app.utils.date.patterns.DataTimePattern;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DataTimeGenerator {
    public static String getDate(DataTimePattern dataTimePattern) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dataTimePattern.getPattern());
        return currentDate.format(formatter);
    }


    public static String getDateTime(DataTimePattern dataTimePattern) {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dataTimePattern.getPattern());
        return currentDate.format(formatter);
    }
}
