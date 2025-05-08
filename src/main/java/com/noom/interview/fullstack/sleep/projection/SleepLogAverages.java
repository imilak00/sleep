package com.noom.interview.fullstack.sleep.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SleepLogAverages {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalTime averageStartTime;
    private LocalTime averageEndTime;
    private LocalTime averageDuration;
    private List<MoodFrequency> moodFrequencies;

}