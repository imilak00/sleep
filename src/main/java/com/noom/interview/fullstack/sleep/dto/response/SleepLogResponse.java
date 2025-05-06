package com.noom.interview.fullstack.sleep.dto.response;

import com.noom.interview.fullstack.sleep.model.Mood;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class SleepLogResponse {

    private LocalDate date;

    private Instant startTime;

    private Instant endTime;

    private Mood mood;

    private String timezone;

    private String duration;

}