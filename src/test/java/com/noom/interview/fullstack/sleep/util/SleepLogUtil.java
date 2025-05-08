package com.noom.interview.fullstack.sleep.util;

import com.noom.interview.fullstack.sleep.dto.request.SleepLogCreateRequest;
import com.noom.interview.fullstack.sleep.model.Mood;
import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.model.User;
import com.noom.interview.fullstack.sleep.projection.DateRangeWithAverageDuration;
import com.noom.interview.fullstack.sleep.projection.MoodFrequency;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static com.noom.interview.fullstack.sleep.util.UserUtil.generateUserStub;

public class SleepLogUtil {
    public static final long TEST_ID = 1L;
    public static final LocalDate TEST_LOG_DATE = LocalDate.parse("2025-01-11");
    public static final Instant TEST_START_TIME = Instant.parse("2025-01-10T20:00:00z");
    public static final LocalDate TEST_DATE_FROM = LocalDate.parse("2024-01-10");
    public static final LocalDate TEST_DATE_TO = LocalDate.parse("2025-01-11");
    public static final Instant TEST_END_TIME = Instant.parse("2025-01-11T06:00:00z");
    public static final Double TEST_DURATION_MINUTES = 600.0;
    public static final Mood TEST_MOOD = Mood.OK;
    public static final String TEST_TIMEZONE = "Europe/Zagreb";

    public static SleepLog generateSleepLogStub(Long logId, Mood mood, LocalDate logDate, Instant startTime, Instant endTime, User user) {
        return new SleepLog(logId, logDate, startTime, endTime,
                TEST_DURATION_MINUTES.intValue(), mood, TEST_TIMEZONE, user);
    }

    public static SleepLog generateSleepLogStub(User user) {
        return new SleepLog(null, TEST_LOG_DATE, TEST_START_TIME, TEST_END_TIME,
                TEST_DURATION_MINUTES.intValue(), TEST_MOOD, TEST_TIMEZONE, user);
    }

    public static List<SleepLog> generateSleepLogListStub() {
        User user = generateUserStub();
        return List.of(generateSleepLogStub(TEST_ID, TEST_MOOD, TEST_LOG_DATE, TEST_START_TIME, TEST_END_TIME, user));
    }

    public static MoodFrequency generateMoodFrequencyStub() {
        return new MoodFrequency(Mood.OK, 1L);
    }

    public static DateRangeWithAverageDuration generateDateRangeWithAverageDuration() {
        return new DateRangeWithAverageDuration(TEST_DATE_FROM, TEST_DATE_TO, TEST_DURATION_MINUTES);
    }

    public static List<MoodFrequency> generateMoodFrequencyListStub() {
        return List.of(generateMoodFrequencyStub());
    }

    public static SleepLogCreateRequest generateSleepLogCreateRequest() {
        return new SleepLogCreateRequest(TEST_ID, TEST_LOG_DATE, TEST_START_TIME, TEST_END_TIME, TEST_MOOD, TEST_TIMEZONE);
    }

}