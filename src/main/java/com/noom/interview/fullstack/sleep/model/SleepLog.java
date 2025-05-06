package com.noom.interview.fullstack.sleep.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "sleep_logs")
@Getter
@Setter
public class SleepLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate logDate;

    private Instant startTime;

    private Instant endTime;

    private Integer durationMinutes;

    @Enumerated(EnumType.STRING)
    private Mood mood;

    private String timezone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
