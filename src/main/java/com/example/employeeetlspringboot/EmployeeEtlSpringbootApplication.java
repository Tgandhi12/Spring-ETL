package com.example.employeeetlspringboot;

import com.example.employeeetlspringboot.entity.source.SourceEmployee;
import com.example.employeeetlspringboot.repository.source.SourceEmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeEtlSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeEtlSpringbootApplication.class, args);
    }

    @Bean
    CommandLineRunner seedSourceDb(SourceEmployeeRepository repo) {
        return args -> {
            repo.save(SourceEmployee.builder()
                    .name("John")
                    .email("john@gmail.com")
                    .salary(45000.0)
                    .build());

            repo.save(SourceEmployee.builder()
                    .name("Sara")
                    .email("sara@gmail.com")
                    .salary(55000.0)
                    .build());

            repo.save(SourceEmployee.builder()
                    .name("Mike")
                    .email("mike@gmail.com")
                    .salary(50000.0)
                    .build());
        };
    }
}