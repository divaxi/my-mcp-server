/*
 * Copyright 2024 - 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spring.ai.Tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import com.spring.ai.dto.PagingList;
import com.spring.ai.dto.Employee.EmployeeResponse;
import com.spring.ai.dto.Query.QueryRequest;
import com.spring.ai.repository.EmployeeRepositoryQueryDsl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EmployeeTool {

    private final EmployeeRepositoryQueryDsl employeeRepositoryQueryDsl;

    @Tool(description = "Execute QueryDSL query to filtering Employee", name = "employees://execute-query")
    public PagingList<EmployeeResponse> filterEmployeeQueryDSL(
            @ToolParam(description = "Query request for filtering employees, the instructions can be found at employees://execute-query-instructions ") QueryRequest queryRequest) {
        return employeeRepositoryQueryDsl.filterEmployeeQueryDsl(queryRequest);
    }

    // @Bean
    // public List<McpServerFeatures.SyncToolSpecification> employeeTools() {

    // List<McpSchema.Role> audience = List.of(McpSchema.Role.USER);
    // McpSchema.Annotations annotations = new McpSchema.Annotations(audience, 1.0);

    // String metadata = loadMarkdown("FieldMap.md");

    // var employeeTool = new McpSchema.Tool(
    // "employees://execute-query",
    // "Execute QueryDSL query to filter employees",
    // metadata);

    // return List.of(new McpServerFeatures.SyncToolSpecification(employeeTool,
    // (exchange, args) -> {
    // QueryRequest qr = ((QueryRequest) exchange);
    // var result = employeeRepositoryQueryDsl.filterEmployeeQueryDsl(qr);
    // return new CallToolResult(result, false);
    // }));
    // }

    // private String loadMarkdown(String path) {

    // try (InputStream inputStream = getClass().getClassLoader()
    // .getResourceAsStream(String.format("markdown/employee/%s", path))) {

    // if (inputStream == null) {
    // throw new RuntimeException(
    // String.format("Không tìm thấy file markdown/employee/%s", path));
    // }

    // return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    // } catch (IOException e) {
    // throw new RuntimeException("Lỗi đọc markdown: " + e.getMessage(), e);
    // }

    // }

}