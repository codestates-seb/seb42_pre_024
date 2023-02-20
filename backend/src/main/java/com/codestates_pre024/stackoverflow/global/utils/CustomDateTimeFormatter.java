package com.codestates_pre024.stackoverflow.global.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeFormatter{

    private CustomDateTimeFormatter() {}
    private static final String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
    public static LocalDateTime formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        String formattedDateTime = dateTime.format(formatter);
        return LocalDateTime.parse(formattedDateTime, formatter);
    }

}
