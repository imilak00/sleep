package com.noom.interview.fullstack.sleep.validation;

import com.noom.interview.fullstack.sleep.dto.request.SleepLogCreateRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, SleepLogCreateRequest> {

    @Override
    public boolean isValid(SleepLogCreateRequest value, ConstraintValidatorContext context) {
        if (value.getStartTime() == null || value.getEndTime() == null) {
            return true;
        }
        return !value.getEndTime().isBefore(value.getStartTime());
    }

}