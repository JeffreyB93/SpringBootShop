package com.example.springbootshop.service.validator.impl;

import com.example.springbootshop.service.validator.BaseValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BaseValidatorImpl implements BaseValidator {
    @Override
    public boolean isValidPassword(String strPass) {
        return strPass != null && strPass.length() > 4;
    }

    @Override
    public boolean isValidBalance(BigDecimal balance) {
        return balance.compareTo(BigDecimal.ZERO) >= 0;// TODO: 21.04.2023 Спросить у Виктора
    }
}
