package com.example.employeeetlspringboot.repository.target;

import com.example.employeeetlspringboot.entity.target.TargetEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TargetEmployeeRepository extends JpaRepository<TargetEmployee, Long> {
    Optional<TargetEmployee> findByEmail(String email);
    boolean existsByEmail(String email);
}