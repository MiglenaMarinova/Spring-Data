package com.example.automappingdto.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.orm.hibernate5.SpringFlushSynchronization;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidationUtilImpl implements ValidationUtil {

    private final Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation
                .buildDefaultValidatorFactory().getValidator();
    }


    @Override
    public <E> boolean isEmpty(E entity) {
        return validator.validate(entity).isEmpty();
    }

    @Override
    public <E> Set<ConstraintViolation<E>> violations(E entity) {

        return validator.validate(entity);
    }
}
