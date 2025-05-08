package com.noom.interview.fullstack.sleep.service;

import com.noom.interview.fullstack.sleep.dto.request.SleepLogCreateRequest;
import com.noom.interview.fullstack.sleep.mapper.ObjectMapper;
import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.model.User;
import com.noom.interview.fullstack.sleep.projection.DateRangeWithAverageDuration;
import com.noom.interview.fullstack.sleep.projection.MoodFrequency;
import com.noom.interview.fullstack.sleep.projection.SleepLogAverages;
import com.noom.interview.fullstack.sleep.repository.SleepLogRepository;
import com.noom.interview.fullstack.sleep.util.TimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SleepLogServiceImpl implements SleepLogService {

    private final SleepLogRepository sleepLogRepository;

    private final UserService userService;

    private final ObjectMapper<SleepLogCreateRequest, SleepLog> sleepLogCreateRequestToSleepLogMapper;

    @Override
    public SleepLog getByLogDate(Long userId, LocalDate logDate) {
        User user = userService.getById(userId);

        return sleepLogRepository.findByUserAndLogDate(user, logDate).orElseThrow(
                () -> new EntityNotFoundException("There is no existing sleep log for the requested date."));
    }

    @Override
    public SleepLogAverages getAverages(LocalDate dateFrom, LocalDate dateTo, Long userId) {
        DateRangeWithAverageDuration rangeWithDuration = sleepLogRepository.getDateRangeWithAverageDuration(userId, dateFrom, dateTo);
        if (rangeWithDuration == null || rangeWithDuration.getDateFrom() == null) {
            throw new NoResultException("There are no existing sleep logs for this date range.");
        }

        List<SleepLog> sleepLogs = sleepLogRepository.getSleepLogsByUserIdAndLogDateBetween(userId, dateFrom, dateTo);
        List<LocalTime> startTimes = extractTimes(sleepLogs, SleepLog::getStartTime);
        List<LocalTime> endTimes = extractTimes(sleepLogs, SleepLog::getEndTime);

        LocalTime averageStartTime = getAverageTime(startTimes);
        LocalTime averageEndTime = getAverageTime(endTimes);

        LocalTime averageDuration = TimeFormatter.formatTime(rangeWithDuration.getAverageDuration().intValue());

        List<MoodFrequency> moodFrequencies = sleepLogRepository.getMoodFrequencies(userId, dateFrom, dateTo);

        return new SleepLogAverages(rangeWithDuration.getDateFrom(), rangeWithDuration.getDateTo(), averageStartTime,
                averageEndTime, averageDuration, moodFrequencies);
    }

    @Override
    public SleepLog save(SleepLogCreateRequest request) {
        User user = userService.getById(request.getUserId());
        if (sleepLogRepository.findByUserAndLogDate(user, request.getLogDate()).isPresent()) {
            throw new EntityExistsException("There is already an existing log for this date.");
        }

        return sleepLogRepository.save(sleepLogCreateRequestToSleepLogMapper.map(request));
    }

    private List<LocalTime> extractTimes(List<SleepLog> list, Function<SleepLog, Instant> timeExtractor) {
        ZoneId utc = ZoneId.of("UTC");
        return list.stream()
                .map(obj -> timeExtractor.apply(obj).atZone(utc).toLocalTime())
                .collect(Collectors.toList());
    }

    private LocalTime getAverageTime(List<LocalTime> times) {
        final int SECONDS_IN_DAY = 86400;
        final double FULL_CIRCLE = 2 * Math.PI;

        double sumSin = 0.0;
        double sumCos = 0.0;

        for (LocalTime time : times) {
            int seconds = time.toSecondOfDay();
            double angle = (seconds / (double) SECONDS_IN_DAY) * FULL_CIRCLE;
            sumSin += Math.sin(angle);
            sumCos += Math.cos(angle);
        }

        double avgAngle = Math.atan2(sumSin / times.size(), sumCos / times.size());
        avgAngle = (avgAngle + FULL_CIRCLE) % FULL_CIRCLE;

        int avgSeconds = (int) Math.round((avgAngle / FULL_CIRCLE) * SECONDS_IN_DAY);

        return LocalTime.ofSecondOfDay(avgSeconds);
    }

}