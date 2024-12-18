package com.example.validationdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String prefix;

    @Override
    public void initialize(CourseCode courseCode) {
        prefix = courseCode.value();
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) { // 1st param: user input in http form, 2nd param: additional error message
        boolean result;

        if(code != null){
            result = code.startsWith(prefix);
        } else {
            result = false;
        }

        return result;
    }
}
