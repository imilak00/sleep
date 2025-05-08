package com.noom.interview.fullstack.sleep.validation;

import com.noom.interview.fullstack.sleep.dto.request.SleepLogCreateRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;

public class Within24HoursValidator implements ConstraintValidator<Within24Hours, SleepLogCreateRequest> {

    @Override
    public boolean isValid(SleepLogCreateRequest value, ConstraintValidatorContext context) {
        Duration diff = Duration.between(value.getStartTime(), value.getEndTime()).abs();

        return diff.toHours() < 24;
    }
}