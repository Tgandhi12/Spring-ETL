package com.example.employeeetlspringboot.service;

import com.example.employeeetlspringboot.dto.EmployeeRequest;
import com.example.employeeetlspringboot.entity.target.TargetEmployee;
import com.example.employeeetlspringboot.exception.DuplicateEmployeeException;
import com.example.employeeetlspringboot.exception.EmployeeNotFoundException;
import com.example.employeeetlspringboot.repository.target.TargetEmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final TargetEmployeeRepository targetEmployeeRepository;
    private final ValidationService validationService;

    public TargetEmployee createEmployee(EmployeeRequest request) {
        validationService.validate(request.getName(), request.getEmail(), request.getSalary());

        if (targetEmployeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmployeeException("Employee with email already exists");
        }

        TargetEmployee employee = TargetEmployee.builder()
                .name(request.getName())
                .email(request.getEmail())
                .salary(request.getSalary())
                .build();

        log.info("Creating employee with email: {}", request.getEmail());
        return targetEmployeeRepository.save(employee);
    }

    public List<TargetEmployee> getAllEmployees() {
        log.info("Fetching all employees");
        return targetEmployeeRepository.findAll();
    }

    public TargetEmployee getEmployeeById(Long id) {
        return targetEmployeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    public TargetEmployee updateEmployee(Long id, EmployeeRequest request) {
        validationService.validate(request.getName(), request.getEmail(), request.getSalary());

        TargetEmployee employee = targetEmployeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        if (!employee.getEmail().equals(request.getEmail()) &&
                targetEmployeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmployeeException("Another employee with this email already exists");
        }

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());

        log.info("Updating employee id: {}", id);
        return targetEmployeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        TargetEmployee employee = targetEmployeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        log.info("Deleting employee id: {}", id);
        targetEmployeeRepository.delete(employee);
    }
}