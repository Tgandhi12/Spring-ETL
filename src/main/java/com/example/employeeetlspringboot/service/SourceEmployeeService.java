package com.example.employeeetlspringboot.service;

import com.example.employeeetlspringboot.entity.source.SourceEmployee;
import com.example.employeeetlspringboot.exception.EmployeeNotFoundException;
import com.example.employeeetlspringboot.repository.source.SourceEmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SourceEmployeeService {

    private final SourceEmployeeRepository sourceEmployeeRepository;

    public SourceEmployee createEmployee(SourceEmployee employee) {
        log.info("Creating raw source employee: {}", employee.getEmail());
        return sourceEmployeeRepository.save(employee);
    }

    public List<SourceEmployee> getAllEmployees() {
        return sourceEmployeeRepository.findAll();
    }

    public SourceEmployee getEmployeeById(Long id) {
        return sourceEmployeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Source employee not found with id: " + id));
    }

    public SourceEmployee updateEmployee(Long id, SourceEmployee updatedEmployee) {
        SourceEmployee employee = sourceEmployeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Source employee not found with id: " + id));

        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setSalary(updatedEmployee.getSalary());

        log.info("Updating raw source employee id: {}", id);
        return sourceEmployeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        SourceEmployee employee = sourceEmployeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Source employee not found with id: " + id));

        log.info("Deleting raw source employee id: {}", id);
        sourceEmployeeRepository.delete(employee);
    }
}