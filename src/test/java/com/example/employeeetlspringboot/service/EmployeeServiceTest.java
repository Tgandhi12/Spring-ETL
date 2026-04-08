package com.example.employeeetlspringboot.service;

import com.example.employeeetlspringboot.dto.EmployeeRequest;
import com.example.employeeetlspringboot.entity.target.TargetEmployee;
import com.example.employeeetlspringboot.repository.target.TargetEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Test
    void shouldCreateEmployee(){
        TargetEmployeeRepository repo = mock(TargetEmployeeRepository.class);
        ValidationService validationService = new ValidationService();
        EmployeeService service = new EmployeeService(repo ,validationService);
        EmployeeRequest request = new EmployeeRequest();
        request.setName("Tanishq");
        request.setEmail("tanishq@gmail.com");
        request.setSalary(30000.0);

        when(repo.existsByEmail("tanishq@gmail.com")).thenReturn(false);
        when(repo.save(Mockito.any(TargetEmployee.class)))
                .thenAnswer(invocation->invocation.getArgument(0));
        TargetEmployee saved = service.createEmployee(request);
        assertEquals("Tanishq",saved.getName());
        assertEquals("tanishq@gmail.com",saved.getEmail());
    }
    @Test
    void shouldGetEmployeeById(){
        TargetEmployeeRepository repo = mock(TargetEmployeeRepository.class);
        ValidationService validationService = new ValidationService();
        EmployeeService service = new EmployeeService(repo, validationService);

        TargetEmployee employee = TargetEmployee.builder()
                .id(1L)
                .name("aman")
                .email("aman@gmail.com")
                .salary(20000.0)
                .build();
        when(repo.findById(1L)).thenReturn(Optional.of(employee));
        TargetEmployee result = service.getEmployeeById(1L);
        assertEquals("aman",result.getName());

    }
}
