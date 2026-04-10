package com.example.employeeetlspringboot.util;

import com.example.employeeetlspringboot.dto.EmployeeRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JsonEmployeeReader {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public  List<EmployeeRequest> read(String filename) {
        try {
            ClassPathResource resource = new ClassPathResource(filename);
            InputStream inputStream = resource.getInputStream();
            return objectMapper.readValue(inputStream, new TypeReference<List<EmployeeRequest>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + e.getMessage(), e);
        }
    }
}
