package com.example.employeeetlspringboot.service;

import com.example.employeeetlspringboot.dto.EmployeeRequest;
import com.example.employeeetlspringboot.entity.target.TargetEmployee;
import com.example.employeeetlspringboot.repository.target.TargetEmployeeRepository;
import com.example.employeeetlspringboot.util.CsvEmployeeReader;
import com.example.employeeetlspringboot.util.InvalidRecordWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilePipelineService {

    private final CsvEmployeeReader csvEmployeeReader;
    private final InvalidRecordWriter invalidRecordWriter;
    private final TargetEmployeeRepository targetEmployeeRepository;
    private final ValidationService validationService;

    public String importFromCsv(String inputPath, String invalidOutputPath) {
        int inserted = 0;
        int invalid = 0;
        int duplicates = 0;

        try {
            List<EmployeeRequest> employees = csvEmployeeReader.read(inputPath);

            for (EmployeeRequest request : employees) {
                String rawRecord = request.getName() + "," + request.getEmail() + "," + request.getSalary();

                try {
                    String normalizedName = request.getName() == null ? null : request.getName().trim();
                    String normalizedEmail = request.getEmail() == null ? null : request.getEmail().trim().toLowerCase();
                    Double salary = request.getSalary();

                    validationService.validate(normalizedName, normalizedEmail, salary);

                    if (targetEmployeeRepository.existsByEmail(normalizedEmail)) {
                        duplicates++;
                        invalidRecordWriter.write(invalidOutputPath, rawRecord, "Duplicate email");
                        log.warn("Duplicate CSV record skipped: {}", normalizedEmail);
                        continue;
                    }

                    TargetEmployee employee = TargetEmployee.builder()
                            .name(normalizedName)
                            .email(normalizedEmail)
                            .salary(salary)
                            .build();

                    targetEmployeeRepository.save(employee);
                    inserted++;
                    log.info("Inserted CSV employee: {}", normalizedEmail);

                } catch (Exception e) {
                    invalid++;
                    invalidRecordWriter.write(invalidOutputPath, rawRecord, e.getMessage());
                    log.warn("Invalid CSV record skipped: {}", rawRecord);
                }
            }

        } catch (Exception e) {
            log.error("CSV import failed: {}", e.getMessage());
            return "CSV import failed: " + e.getMessage();
        }

        return "CSV import completed. Inserted: " + inserted +
                ", Invalid: " + invalid +
                ", Duplicates: " + duplicates;
    }
}