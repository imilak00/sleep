package com.noom.interview.fullstack.sleep.projection;

import com.noom.interview.fullstack.sleep.util.DurationUtil;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class SleepLogAverages {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalTime averageStartTime;
    private LocalTime averageEndTime;
    private String averageDuration;

    private List<MoodFrequency> moodCountFrequencies;

    SleepLogAverages(Date dateFrom, Date dateTo, BigDecimal averageDuration, Time averageStartTime, Time averageEndTime) {
        this.dateFrom = dateFrom != null ? dateFrom.toLocalDate() : null;
        this.dateTo = dateTo != null ? dateTo.toLocalDate() : null;
        this.averageDuration = averageDuration != null ? DurationUtil.formatDuration(averageDuration.intValue()) : null;
        this.averageStartTime = averageStartTime != null ? averageStartTime.toLocalTime() : null;
        this.averageEndTime = averageEndTime != null ? averageEndTime.toLocalTime() : null;
    }
}