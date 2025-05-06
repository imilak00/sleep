package com.noom.interview.fullstack.sleep.dto.request;

import com.noom.interview.fullstack.sleep.model.Mood;
import com.noom.interview.fullstack.sleep.validation.ValidDateRange;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@ValidDateRange
public class SleepLogCreateRequest {

    @NotNull
    private Long userId;

    @NotNull
    private LocalDate logDate;

    @NotNull
    @Past
    private Instant startTime;

    @NotNull
    @Past
    private Instant endTime;

    @NotNull
    private Mood mood;

    @NotBlank
    private String timezone;

}
