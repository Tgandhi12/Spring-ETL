package com.example.employeeetlspringboot.service;

import com.example.employeeetlspringboot.exception.InvalidEmployeeException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidationService {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public void validate(String name, String email, Double salary) {
        if (name == null || name.isBlank()) {
            throw new InvalidEmployeeException("Name cannot be blank");
        }
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmployeeException("Invalid email format");
        }
        if (salary == null || salary <= 0) {
            throw new InvalidEmployeeException("Salary must be greater than 0");
        }
    }
}