package com.codestates_pre024.stackoverflow.global.utils;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeFormatter{

    /** private constructor. Protected to prevent direct instantiation. **/
    private CustomDateTimeFormatter() {}

    /** Prevent external access change of dateTimePattern. **/
    private static final String dateTimePattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * Return the {@code LocalDateTime} formatted with dateTimePattern.
     * @param dateTime unformatted value of dateTime
     * @return LocalDateTime formatted with dateTimePattern.
     */
    public static LocalDateTime formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        String formattedDateTime = dateTime.format(formatter);
        return LocalDateTime.parse(formattedDateTime, formatter);
    }

}
