package com.example.employeeetlspringboot.service;

import com.example.employeeetlspringboot.exception.InvalidEmployeeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {

    private final ValidationService validationService = new ValidationService();

    @Test
    void shouldPassForValidEmployee() {
        assertDoesNotThrow(() ->
                validationService.validate("Tanishq", "tanishq@gmail.com", 50000.0));
    }

    @Test
    void shouldFailForInvalidEmail() {
        assertThrows(InvalidEmployeeException.class, () ->
                validationService.validate("Tanishq", "wrong-email", 50000.0));
    }

    @Test
    void shouldFailForNegativeSalary() {
        assertThrows(InvalidEmployeeException.class, () ->
                validationService.validate("Tanishq", "tanishq@gmail.com", -50.0));
    }
}