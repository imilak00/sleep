package com.noom.interview.fullstack.sleep.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DateRangeWithAverageDuration {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Double averageDuration;

}
