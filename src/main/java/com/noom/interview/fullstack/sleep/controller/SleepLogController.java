package com.noom.interview.fullstack.sleep.controller;

import com.noom.interview.fullstack.sleep.dto.request.SleepLogCreateRequest;
import com.noom.interview.fullstack.sleep.dto.response.SleepLogResponse;
import com.noom.interview.fullstack.sleep.mapper.ObjectMapper;
import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.projection.SleepLogAverages;
import com.noom.interview.fullstack.sleep.service.SleepLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/sleep-log")
@RequiredArgsConstructor
public class SleepLogController {

    private final SleepLogService sleepLogService;

    private final ObjectMapper<SleepLog, SleepLogResponse> sleepLogToSleepLogResponseMapper;

    @GetMapping
    public ResponseEntity<SleepLogResponse> getByLogDate(@RequestParam Long userId,
                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate logDate) {
        SleepLog sleepLog = sleepLogService.getByLogDate(userId, logDate);
        SleepLogResponse response = sleepLogToSleepLogResponseMapper.map(sleepLog);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/averages")
    public ResponseEntity<SleepLogAverages> getAverages(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
                                                        @RequestParam Long userId) {
        SleepLogAverages response = sleepLogService.getAverages(dateFrom, dateTo, userId);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SleepLogResponse> save(@RequestBody @Valid SleepLogCreateRequest request) {
        SleepLog sleepLog = sleepLogService.save(request);
        SleepLogResponse response = sleepLogToSleepLogResponseMapper.map(sleepLog);

        return ResponseEntity.ok(response);
    }

}