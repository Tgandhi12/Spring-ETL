package com.example.employeeetlspringboot.service;

import com.example.employeeetlspringboot.entity.source.SourceEmployee;
import com.example.employeeetlspringboot.entity.target.TargetEmployee;
import com.example.employeeetlspringboot.repository.source.SourceEmployeeRepository;
import com.example.employeeetlspringboot.repository.target.TargetEmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DbTransferService {

    private final SourceEmployeeRepository sourceEmployeeRepository;
    private final TargetEmployeeRepository targetEmployeeRepository;
    private final ValidationService validationService;

    public String transferEmployees() {
        List<SourceEmployee> sourceEmployees = sourceEmployeeRepository.findAll();
        int inserted = 0;
        int skipped = 0;

        for (SourceEmployee source : sourceEmployees) {
            try {
                String transformedName = source.getName() == null ? null : source.getName().trim();
                String transformedEmail = source.getEmail() == null ? null : source.getEmail().trim().toLowerCase();
                Double transformedSalary = source.getSalary();

                validationService.validate(transformedName, transformedEmail, transformedSalary);

                if (targetEmployeeRepository.existsByEmail(transformedEmail)) {
                    skipped++;
                    log.warn("Duplicate skipped: {}", transformedEmail);
                    continue;
                }

                TargetEmployee target = TargetEmployee.builder()
                        .name(transformedName)
                        .email(transformedEmail)
                        .salary(transformedSalary)
                        .build();

                targetEmployeeRepository.save(target);
                inserted++;
                log.info("Transferred employee: {}", transformedEmail);

            } catch (Exception e) {
                skipped++;
                log.error("Failed to transfer source employee id {}: {}", source.getId(), e.getMessage());
            }
        }

        return "Transfer completed. Inserted: " + inserted + ", Skipped: " + skipped;
    }
}