package com.example.springbootshop.service.validator;

import java.math.BigDecimal;

public interface BaseValidator {

    boolean isValidPassword(String strPass);

    boolean isValidBalance(BigDecimal balance);
}
