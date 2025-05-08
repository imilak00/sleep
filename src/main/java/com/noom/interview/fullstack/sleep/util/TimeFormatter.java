package com.noom.interview.fullstack.sleep.util;

import java.time.LocalTime;

public class TimeFormatter {

    public static LocalTime formatTime(Integer totalMinutes) {
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;

        return LocalTime.of(hours, minutes);
    }

}
