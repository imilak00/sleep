package com.noom.interview.fullstack.sleep.projection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DateRangeWithAverageDuration {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer averageDuration;

    public DateRangeWithAverageDuration(LocalDate dateFrom, LocalDate dateTo, Double averageDuration) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.averageDuration = averageDuration.intValue();
    }

}
