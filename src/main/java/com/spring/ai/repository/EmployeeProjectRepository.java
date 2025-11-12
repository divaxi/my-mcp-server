package com.spring.ai.repository;

import org.springframework.stereotype.Repository;

import com.spring.ai.model.EmployeeProject;
import com.spring.ai.model.EmployeeProjectId;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, EmployeeProjectId> {

}
