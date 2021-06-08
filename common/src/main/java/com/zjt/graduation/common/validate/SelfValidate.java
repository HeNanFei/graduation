package com.zjt.graduation.common.validate;

import com.zjt.graduation.common.annota.AutoValidate;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/12 14:27
 */
@Component
public class SelfValidate implements ConstraintValidator<AutoValidate, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
