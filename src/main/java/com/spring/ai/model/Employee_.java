package com.spring.ai.model;

import java.time.LocalDate;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Employee.class)
public class Employee_ {

    public static volatile SingularAttribute<Employee, Long> employeeId;
    public static volatile SingularAttribute<Employee, String> firstName;
    public static volatile SingularAttribute<Employee, String> lastName;
    public static volatile SingularAttribute<Employee, String> email;
    public static volatile SingularAttribute<Employee, String> phone;
    public static volatile SingularAttribute<Employee, LocalDate> hireDate;
    public static volatile SingularAttribute<Employee, Long> managerId;
    public static volatile SingularAttribute<Employee, String> jobTitle;
    public static volatile SingularAttribute<Employee, Double> salary;
}

