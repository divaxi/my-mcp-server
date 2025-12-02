package com.spring.ai.repository;

import java.time.Duration;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.ai.dto.PagingList;
import com.spring.ai.dto.ResponseChunk;
import com.spring.ai.dto.Employee.EmployeeResponse;
import com.spring.ai.dto.Employee.QEmployeeResponse;
import com.spring.ai.dto.Query.QueryRequest;
import com.spring.ai.mapper.EmployeeFieldMap;
import com.spring.ai.model.QEmployee;
import com.spring.ai.model.QEmployeeProject;
import com.spring.ai.model.QProject;
import com.spring.ai.util.builder.QueryDSLBuilder;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EmployeeRepositoryQueryDsl {

        private final JPAQueryFactory queryFactory;
        private QueryDSLBuilder queryDSLBuilder;
        private final QEmployee qEmployee = QEmployee.employee;
        private final QEmployeeProject qEmployeeProject = QEmployeeProject.employeeProject;
        private final QProject qProject = QProject.project;

        @PostConstruct
        public void init() {
                this.queryDSLBuilder = new QueryDSLBuilder(new EmployeeFieldMap(qEmployee, qEmployeeProject, qProject));
        }

        public Flux<ResponseChunk> filterEmployeeQueryDSL(QueryRequest req) {

                Mono<ResponseChunk> start = Mono.just(new ResponseChunk("start", null))
                                .delayElement(Duration.ofSeconds(1)); // non-blocking delay

                Mono<ResponseChunk> result = Mono.fromCallable(() -> new ResponseChunk("query executed",null));

                return Flux.concat(start, result);
        }

        public PagingList<EmployeeResponse> query(QueryRequest queryRequest) {

                // Construct the projection DTO with string aggregation for projects
                QEmployeeResponse employeeResponseTable = new QEmployeeResponse(
                                qEmployee.employeeId,
                                qEmployee.firstName,
                                qEmployee.lastName,
                                qEmployee.email,
                                qEmployee.hireDate,
                                qEmployee.manager.firstName,
                                qEmployee.manager.lastName,
                                qEmployee.department.departmentName,
                                qEmployee.salary,
                                Expressions.stringTemplate("string_agg({0}||'%'||{1}, ',')", qProject.projectName,
                                                qEmployeeProject.role) // Aggregate
                // projects
                // into a
                // single
                // string
                );

                Predicate queryPredicate = queryDSLBuilder.create(queryRequest);
                // Build the template query
                JPAQuery<EmployeeResponse> query = queryFactory
                                .select(employeeResponseTable)
                                .from(qEmployee)
                                .leftJoin(qEmployee.manager)
                                .leftJoin(qEmployee.department)
                                .leftJoin(qEmployee.projects, qEmployeeProject)
                                .leftJoin(qEmployeeProject.project, qProject)
                                // GROUP BY required for aggregate function string_agg
                                .groupBy(
                                                qEmployee.employeeId,
                                                qEmployee.firstName,
                                                qEmployee.lastName,
                                                qEmployee.email,
                                                qEmployee.hireDate,
                                                qEmployee.manager.firstName,
                                                qEmployee.manager.lastName,
                                                qEmployee.department.departmentName,
                                                qEmployee.salary)
                                .where(queryPredicate)
                                // Pagination using offset and limit
                                .offset(queryRequest.getPage() * queryRequest.getLimit())
                                .limit(queryRequest.getLimit());

                // Build the dynamic predicate based on QueryRequest

                // Execute query and fetch results
                List<EmployeeResponse> queryResult = query.fetch();

                // paginated result including total count for paging
                PagingList<EmployeeResponse> result = new PagingList<EmployeeResponse>(
                                queryResult,
                                countQuery(queryPredicate).fetchOne(), // Total count matching the predicate
                                queryRequest.getLimit(),
                                queryRequest.getPage());

                return result;
        }

        /**
         * Count the total number of employees matching the predicate for pagination.
         * 
         * @param queryPredicate The dynamic filter predicate
         * @return JPAQuery<Long> representing the count query
         */
        private JPAQuery<Long> countQuery(Predicate queryPredicate) {

                return queryFactory
                                .select(qEmployee.employeeId.countDistinct())
                                .from(qEmployee)
                                .leftJoin(qEmployee.manager)
                                .leftJoin(qEmployee.department)
                                .leftJoin(qEmployee.projects, qEmployeeProject)
                                .leftJoin(qEmployeeProject.project, qProject)
                                .where(queryPredicate);
        }
}
