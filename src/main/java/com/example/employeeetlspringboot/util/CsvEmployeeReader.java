package com.example.employeeetlspringboot.util;

import com.example.employeeetlspringboot.dto.EmployeeRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvEmployeeReader {

    public List<EmployeeRequest> read(String filePath) throws IOException {
        List<EmployeeRequest> employees = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length < 3) {
                continue;
            }

            EmployeeRequest request = new EmployeeRequest();
            request.setName(parts[0].trim());
            request.setEmail(parts[1].trim());
            request.setSalary(Double.parseDouble(parts[2].trim()));

            employees.add(request);
        }

        return employees;
    }
}