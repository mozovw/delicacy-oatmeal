package com.delicacy.oatmeal.validation;

import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private String regexp;

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null||"".equals(value)){return false;}
        if( value.matches(regexp)){
            return true;
        }
        return false;
    }

}