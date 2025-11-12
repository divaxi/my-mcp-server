package com.spring.ai.model;

import java.time.LocalDate;

import com.querydsl.core.annotations.QueryProjection;

public record EmployeeResponse(long id, String firstName, String lastName, String email, LocalDate hireDate,
        String managerFirstName, String managerLastName, String departmentName, Double salary, String projectNames) {

    @QueryProjection
    public EmployeeResponse {
    }
}
