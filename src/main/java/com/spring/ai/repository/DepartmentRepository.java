package com.spring.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.stereotype.Repository;

import com.spring.ai.model.Department;
import com.spring.ai.model.QDepartment;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

} 