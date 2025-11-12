package com.spring.ai.repository;

import org.springframework.stereotype.Repository;

import com.spring.ai.model.Project;


import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
