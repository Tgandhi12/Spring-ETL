package com.example.employeeetlspringboot.entity.target;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Double salary;
}