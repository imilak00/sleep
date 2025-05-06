package com.noom.interview.fullstack.sleep.service;

import com.noom.interview.fullstack.sleep.dto.request.SleepLogCreateRequest;
import com.noom.interview.fullstack.sleep.mapper.ObjectMapper;
import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.model.User;
import com.noom.interview.fullstack.sleep.projection.SleepLogAverages;
import com.noom.interview.fullstack.sleep.repository.SleepLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.time.LocalDate;

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
        SleepLogAverages sleepLogAverages = sleepLogRepository.getAverages(userId, dateFrom, dateTo);
        if (sleepLogAverages.getDateFrom() == null) {
            throw new NoResultException("There are no existing sleep logs for this date range.");
        }

        sleepLogAverages.setMoodCountFrequencies(sleepLogRepository.getMoodFrequencies(userId, dateFrom, dateTo));

        return sleepLogAverages;
    }

    @Override
    public SleepLog save(SleepLogCreateRequest request) {
        User user = userService.getById(request.getUserId());
        if (sleepLogRepository.findByUserAndLogDate(user, request.getLogDate()).isPresent()) {
            throw new EntityExistsException("There is already an existing log for this date.");
        }

        return sleepLogRepository.save(sleepLogCreateRequestToSleepLogMapper.map(request));
    }

}