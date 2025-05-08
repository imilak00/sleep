package com.noom.interview.fullstack.sleep.mapper;

import com.noom.interview.fullstack.sleep.dto.response.SleepLogResponse;
import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.util.TimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class SleepLogToSleepLogResponseMapper implements ObjectMapper<SleepLog, SleepLogResponse> {

    @Override
    public SleepLogResponse map(SleepLog source) {
        SleepLogResponse destination = new SleepLogResponse();

        destination.setDate(source.getLogDate());
        destination.setStartTime(source.getStartTime());
        destination.setEndTime(source.getEndTime());
        destination.setDuration(TimeFormatter.formatTime(source.getDurationMinutes()));
        destination.setMood(source.getMood());
        destination.setTimezone(source.getTimezone());

        return destination;
    }

}