package com.noom.interview.fullstack.sleep.mapper;

import com.noom.interview.fullstack.sleep.dto.request.SleepLogCreateRequest;
import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class SleepLogCreateRequestToSleepLogMapper implements ObjectMapper<SleepLogCreateRequest, SleepLog> {

    private final UserService userService;

    @Override
    public SleepLog map(SleepLogCreateRequest source) {
        SleepLog destination = new SleepLog();

        destination.setLogDate(source.getLogDate());
        destination.setStartTime(source.getStartTime());
        destination.setEndTime(source.getEndTime());
        destination.setDurationMinutes((int) (Duration.between(source.getStartTime(), source.getEndTime()).toMinutes()));
        destination.setMood(source.getMood());
        destination.setTimezone(source.getTimezone());
        destination.setUser(userService.getById(source.getUserId()));

        return destination;
    }

}