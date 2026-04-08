package com.example.employeeetlspringboot.repository.source;

import com.example.employeeetlspringboot.entity.source.SourceEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceEmployeeRepository extends JpaRepository<SourceEmployee, Long> {
}