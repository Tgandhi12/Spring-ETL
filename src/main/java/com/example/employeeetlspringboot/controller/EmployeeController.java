package com.example.employeeetlspringboot.controller;

import com.example.employeeetlspringboot.dto.EmployeeRequest;
import com.example.employeeetlspringboot.entity.target.TargetEmployee;
import com.example.employeeetlspringboot.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public TargetEmployee createEmployee(@Valid @RequestBody EmployeeRequest request) {
        return employeeService.createEmployee(request);
    }

    @GetMapping
    public List<TargetEmployee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public TargetEmployee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public TargetEmployee updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request) {
        return employeeService.updateEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }
}