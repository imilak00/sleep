package com.noom.interview.fullstack.sleep.repository;

import com.noom.interview.fullstack.sleep.SleepApplication;
import com.noom.interview.fullstack.sleep.model.Mood;
import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.model.User;
import com.noom.interview.fullstack.sleep.projection.DateRangeWithAverageDuration;
import com.noom.interview.fullstack.sleep.projection.MoodFrequency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static com.noom.interview.fullstack.sleep.util.SleepLogUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles(SleepApplication.UNIT_TEST_PROFILE)
class SleepLogRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SleepLogRepository sleepLogRepository;

    private User user;

    @BeforeEach
    void setup() {
        user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Test
    void shouldFindSleepLogByUserIdAndLogDate() {
        SleepLog sleepLog = generateSleepLogStub(user);
        sleepLogRepository.save(sleepLog);

        Optional<SleepLog> found = sleepLogRepository.findByUserAndLogDate(user, TEST_LOG_DATE);
        assertTrue(found.isPresent());
        assertEquals(TEST_DURATION_MINUTES.intValue(), found.get().getDurationMinutes());
    }

    @Test
    void shouldGetDateRangeWithAverageDuration() {
        sleepLogRepository.save(generateSleepLogStub(null, Mood.OK, TEST_LOG_DATE.minusDays(1), TEST_START_TIME, TEST_END_TIME, user));
        sleepLogRepository.save(generateSleepLogStub(null, Mood.OK, TEST_LOG_DATE, TEST_START_TIME, TEST_END_TIME, user));

        DateRangeWithAverageDuration result = sleepLogRepository.getDateRangeWithAverageDuration(
                user.getId(), TEST_DATE_FROM.minusDays(1), TEST_DATE_TO);

        assertEquals(TEST_LOG_DATE.minusDays(1), result.getDateFrom());
        assertEquals(TEST_LOG_DATE, result.getDateTo());
        assertEquals(TEST_DURATION_MINUTES.intValue(), result.getAverageDuration());
    }

    @Test
    void testGetMoodFrequencies() {
        sleepLogRepository.save(generateSleepLogStub(null, Mood.OK, TEST_LOG_DATE, TEST_START_TIME, TEST_END_TIME, user));
        sleepLogRepository.save(generateSleepLogStub(null, Mood.BAD, TEST_LOG_DATE, TEST_START_TIME, TEST_END_TIME, user));
        sleepLogRepository.save(generateSleepLogStub(null, Mood.GOOD, TEST_LOG_DATE, TEST_START_TIME, TEST_END_TIME, user));

        List<MoodFrequency> moods = sleepLogRepository.getMoodFrequencies(user.getId(), TEST_DATE_FROM, TEST_DATE_TO);

        assertEquals(3, moods.size());
        assertTrue(moods.stream().anyMatch(o -> o.getMood().equals(Mood.OK) && o.getFrequency() == 1));
        assertTrue(moods.stream().anyMatch(o -> o.getMood().equals(Mood.BAD) && o.getFrequency() == 1));
        assertTrue(moods.stream().anyMatch(o -> o.getMood().equals(Mood.GOOD) && o.getFrequency() == 1));
    }

    @Test
    void testGetSleepLogsByUserIdAndLogDateBetween() {
        sleepLogRepository.save(generateSleepLogStub(user));
        sleepLogRepository.save(generateSleepLogStub(user));

        List<SleepLog> sleepLogs = sleepLogRepository.getSleepLogsByUserIdAndLogDateBetween
                (user.getId(), TEST_DATE_FROM, TEST_DATE_TO);

        assertEquals(2, sleepLogs.size());
    }
}