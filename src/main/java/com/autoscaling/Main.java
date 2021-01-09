package com.autoscaling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDateTime d = LocalDateTime.parse("2009-06-15T13:45:30");
        final String format = d.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);// ERROR
        assert "2009-06-15T13:45:30".equals("");
    }
}