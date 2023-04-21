package com.example.springbootshop;

import com.example.springbootshop.service.validator.BaseValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringBootShopApplicationTests {

    @Autowired
    private BaseValidator baseValidator;

    @Test
    void isValidPasswordCorrect() {
        assertTrue(baseValidator.isValidPassword("12345"));
    }

    @Test
    void isValidPasswordIncorrect() {
        assertTrue(baseValidator.isValidPassword("12345"), "Описание ошибки");
    }
}
