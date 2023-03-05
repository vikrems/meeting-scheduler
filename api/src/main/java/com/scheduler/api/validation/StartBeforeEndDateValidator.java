package com.scheduler.api.validation;

import com.scheduler.api.entity.MeetingDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartBeforeEndDateValidator implements
        ConstraintValidator<StartBeforeEndDate, MeetingDto> {

    @Override
    public void initialize(StartBeforeEndDate constraintAnnotation) {

    }

    @Override
    public boolean isValid(MeetingDto value, ConstraintValidatorContext context) {

        return false;
    }
}
