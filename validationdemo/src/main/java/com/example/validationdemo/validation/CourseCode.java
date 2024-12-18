package com.example.validationdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    // define default value
    public String value() default "HOLO";

    // define default error message
    public String message() default "must start with HOLO";

    // group: group related constraints
    public Class<?>[] groups() default {};

    // payloads: provide custom details about validation failure
    public Class<? extends Payload>[] payload() default {};
}
