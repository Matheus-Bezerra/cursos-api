package com.matheus.cursos.cursos_api.adapters.validations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidEnumValidator implements ConstraintValidator<ValidEnum, Object> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidEnum annotation) {
        this.enumClass = annotation.enumClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;
        for (Object enumValue : enumClass.getEnumConstants()) {
            if (enumValue.toString().equals(value.toString())) {
                return true;
            }
        }
        
        return false;
    }
}