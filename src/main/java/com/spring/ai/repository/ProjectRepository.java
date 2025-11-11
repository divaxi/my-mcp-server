package com.spring.ai.repository;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.ai.model.Project;
import com.spring.ai.model.QProject;

import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
