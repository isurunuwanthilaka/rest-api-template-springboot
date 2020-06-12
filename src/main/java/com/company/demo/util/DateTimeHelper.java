package com.company.demo.util;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class DateTimeHelper {
    public DateTimeHelper() {
    }

    public ZonedDateTime now() {
        LocalDateTime now = LocalDateTime.now();
        ZoneId colombo = ZoneId.of("Asia/Colombo");
        ZonedDateTime currentTime = ZonedDateTime.of(now, colombo);
        return currentTime;
    }
}
