package com.spring.ai.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "EMPLOYEES")
public class Employee {

  @Id
  @Column(name = "EMPLOYEE_ID", nullable = false)
  private Long employeeId;

  @Column(name = "FIRST_NAME", length = 50, nullable = false)
  private String firstName;

  @Column(name = "LAST_NAME", length = 50, nullable = false)
  private String lastName;

  @Column(name = "EMAIL", length = 100, nullable = false)
  private String email;

  @Column(name = "PHONE", length = 20, nullable = false)
  private String phone;

  @Temporal(TemporalType.DATE)
  @Column(name = "HIRE_DATE", nullable = false)
  private LocalDate hireDate;

  @Column(name = "MANAGER_ID")
  private Long managerId;

  @Column(name = "JOB_TITLE", length = 100, nullable = false)
  private String jobTitle;

  @Column(name = "SALARY")
  private Double salary;

}


