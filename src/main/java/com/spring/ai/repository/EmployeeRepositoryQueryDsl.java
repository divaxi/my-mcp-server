package com.spring.ai.repository;

import java.util.List;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.ai.model.EmployeeResponse;
import com.spring.ai.model.QEmployee;
import com.spring.ai.model.QEmployeeResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    public List<EmployeeResponse> findEmployeesByDepartmentId(long departmentId) {
        QEmployee employeeTable = new QEmployee("employee");

        List<EmployeeResponse> result = queryFactory
                .select(new QEmployeeResponse(
                        employeeTable.employeeId,
                        employeeTable.firstName,
                        employeeTable.lastName,
                        employeeTable.email,
                        employeeTable.hireDate,
                        employeeTable.manager.firstName,
                        employeeTable.manager.lastName,
                        employeeTable.department.departmentName,
                        employeeTable.salary,
                        Expressions.constant("") 
                ))
                .from(employeeTable)
                .where(employeeTable.department.departmentId.eq(departmentId))
                .fetch();

        return result;

    }

}
