package com.example.springbootshop;


import static org.junit.Assert.*;
import com.example.springbootshop.service.validator.impl.BaseValidatorImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootShopApplicationTests {

    @Test
    void contextLoads_1() {

        BaseValidatorImpl baseValidator = new BaseValidatorImpl();
        assertEquals(true, baseValidator.isValidPassword("12345"));
    }

    @Test
    void contextLoads_2() {

        BaseValidatorImpl baseValidator = new BaseValidatorImpl();
        assertEquals(false, baseValidator.isValidPassword("123"));
    }
}
