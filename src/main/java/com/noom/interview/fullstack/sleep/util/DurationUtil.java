package com.noom.interview.fullstack.sleep.util;

public class DurationUtil {

    public static String formatDuration(Integer durationMinutes) {
        int hours = durationMinutes / 60;
        int minutes = durationMinutes % 60;

        return String.format("%d:%d", hours, minutes);
    }

}
