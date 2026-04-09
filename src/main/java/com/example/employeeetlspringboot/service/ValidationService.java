package com.example.employeeetlspringboot.service;

import com.example.employeeetlspringboot.exception.InvalidEmployeeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
@Slf4j
@Service
public class ValidationService {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public void validate(String name, String email, Double salary) {

        log.debug("Validating employee: {}", email);

        if (name == null || name.isBlank()) {
            log.error("Validation failed: Name is blank");
            throw new InvalidEmployeeException("Name cannot be blank");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            log.error("Validation failed: Invalid email {}", email);
            throw new InvalidEmployeeException("Invalid email format");
        }

        if (salary <= 0) {
            log.error("Validation failed: Salary must be > 0");
            throw new InvalidEmployeeException("Salary must be greater than 0");
        }
    }
}