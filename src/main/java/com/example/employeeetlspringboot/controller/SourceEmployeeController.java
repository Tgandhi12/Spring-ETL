package com.example.employeeetlspringboot.controller;

import com.example.employeeetlspringboot.entity.source.SourceEmployee;
import com.example.employeeetlspringboot.service.SourceEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/source-employees")
@RequiredArgsConstructor
public class SourceEmployeeController {

    private final SourceEmployeeService sourceEmployeeService;

    @PostMapping
    public SourceEmployee createEmployee(@RequestBody SourceEmployee employee) {
        return sourceEmployeeService.createEmployee(employee);
    }

    @GetMapping
    public List<SourceEmployee> getAllEmployees() {
        return sourceEmployeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public SourceEmployee getEmployeeById(@PathVariable Long id) {
        return sourceEmployeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public SourceEmployee updateEmployee(@PathVariable Long id, @RequestBody SourceEmployee employee) {
        return sourceEmployeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        sourceEmployeeService.deleteEmployee(id);
        return "Source employee deleted successfully";
    }
}