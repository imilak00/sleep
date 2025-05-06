package com.noom.interview.fullstack.sleep.service;

import com.noom.interview.fullstack.sleep.dto.request.SleepLogCreateRequest;
import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.projection.SleepLogAverages;

import java.time.LocalDate;

public interface SleepLogService {

    SleepLog getByLogDate(Long userId, LocalDate logDate);

    SleepLogAverages getAverages(LocalDate dateFrom, LocalDate dateTo, Long userId);

    SleepLog save(SleepLogCreateRequest request);

}
