package com.spring.ai.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "DEPARTMENTS")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID", nullable = false)
    private Long departmentId;

    @Column(name = "DEPARTMENT_NAME", length = 100, nullable = false,unique = true)
    private String departmentName;

    @Column(name = "LOCATION", length = 100)
    private String location;
}
