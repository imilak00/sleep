package com.noom.interview.fullstack.sleep.service;

import com.noom.interview.fullstack.sleep.dto.request.SleepLogCreateRequest;
import com.noom.interview.fullstack.sleep.mapper.ObjectMapper;
import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.model.User;
import com.noom.interview.fullstack.sleep.projection.DateRangeWithAverageDuration;
import com.noom.interview.fullstack.sleep.projection.MoodFrequency;
import com.noom.interview.fullstack.sleep.projection.SleepLogAverages;
import com.noom.interview.fullstack.sleep.repository.SleepLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

import static com.noom.interview.fullstack.sleep.util.SleepLogUtil.*;
import static com.noom.interview.fullstack.sleep.util.UserUtil.generateUserStub;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SleepLogServiceTest {

    @Mock
    private SleepLogRepository sleepLogRepository;

    @Mock
    private UserService userService;

    @Mock
    private ObjectMapper<SleepLogCreateRequest, SleepLog> sleepLogCreateRequestToSleepLogMapper;

    @InjectMocks
    private SleepLogServiceImpl sleepLogService;

    @Test
    void getByLogDate_returnsSleepLog_whenExists() {
        User user = generateUserStub();
        SleepLog sleepLog = generateSleepLogStub(TEST_ID, TEST_MOOD, TEST_LOG_DATE, TEST_START_TIME, TEST_END_TIME, user);

        when(userService.getById(TEST_ID)).thenReturn(user);
        when(sleepLogRepository.findByUserAndLogDate(user, TEST_LOG_DATE)).thenReturn(Optional.of(sleepLog));

        SleepLog result = sleepLogService.getByLogDate(TEST_ID, TEST_LOG_DATE);

        assertEquals(sleepLog, result);
    }

    @Test
    void getByLogDate_throwsException_whenNotFound() {
        User user = generateUserStub();

        when(userService.getById(TEST_ID)).thenReturn(user);
        when(sleepLogRepository.findByUserAndLogDate(user, TEST_LOG_DATE)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> sleepLogService.getByLogDate(TEST_ID, TEST_LOG_DATE));
    }

    @Test
    void getAverages_returnsAverages_whenDataExists() {
        DateRangeWithAverageDuration rangeWithDuration = generateDateRangeWithAverageDuration();
        List<SleepLog> logs = generateSleepLogListStub();
        List<MoodFrequency> moods = generateMoodFrequencyListStub();

        when(sleepLogRepository.getDateRangeWithAverageDuration(TEST_ID, TEST_DATE_FROM, TEST_DATE_TO)).thenReturn(rangeWithDuration);
        when(sleepLogRepository.getSleepLogsByUserIdAndLogDateBetween(TEST_ID, TEST_DATE_FROM, TEST_DATE_TO)).thenReturn(logs);
        when(sleepLogRepository.getMoodFrequencies(TEST_ID, TEST_DATE_FROM, TEST_DATE_TO)).thenReturn(moods);

        SleepLogAverages result = sleepLogService.getAverages(TEST_DATE_FROM, TEST_DATE_TO, TEST_ID);

        assertNotNull(result);
        assertEquals(TEST_DATE_FROM, result.getDateFrom());
        assertEquals(TEST_DATE_TO, result.getDateTo());
        assertEquals(moods, result.getMoodFrequencies());
    }

    @Test
    void getAverages_throwsException_whenNoData() {
        when(sleepLogRepository.getDateRangeWithAverageDuration(TEST_ID, TEST_DATE_FROM, TEST_DATE_TO)).thenReturn(null);

        assertThrows(NoResultException.class, () -> sleepLogService.getAverages(TEST_DATE_FROM, TEST_DATE_TO, TEST_ID));
    }

    @Test
    void save_returnsSavedLog_whenNoExistingLog() {
        SleepLogCreateRequest request = generateSleepLogCreateRequest();
        User user = generateUserStub();
        SleepLog sleepLog = generateSleepLogStub(TEST_ID, TEST_MOOD, TEST_LOG_DATE, TEST_START_TIME, TEST_END_TIME, user);

        when(userService.getById(TEST_ID)).thenReturn(user);
        when(sleepLogRepository.findByUserAndLogDate(user, TEST_LOG_DATE)).thenReturn(Optional.empty());
        when(sleepLogCreateRequestToSleepLogMapper.map(request)).thenReturn(sleepLog);
        when(sleepLogRepository.save(sleepLog)).thenReturn(sleepLog);

        SleepLog result = sleepLogService.save(request);

        assertEquals(sleepLog, result);
    }

    @Test
    void save_throwsException_whenLogExists() {
        SleepLogCreateRequest request = generateSleepLogCreateRequest();
        User user = generateUserStub();

        when(userService.getById(TEST_ID)).thenReturn(user);
        when(sleepLogRepository.findByUserAndLogDate(user, TEST_LOG_DATE)).thenReturn(Optional.of(new SleepLog()));

        assertThrows(EntityExistsException.class, () -> sleepLogService.save(request));
    }
}