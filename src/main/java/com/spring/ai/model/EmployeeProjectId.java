package com.spring.ai.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
 public class EmployeeProjectId implements Serializable {
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "PROJECT_ID")
    private Long projectId;
}


