package com.spring.ai.resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;

@Configuration
public class EmployeeResource {

    @Bean
    public List<McpServerFeatures.SyncResourceSpecification> FieldMapResource() {

        List<McpSchema.Role> audience = List.of(McpSchema.Role.ASSISTANT);
        McpSchema.Annotations annotations = new McpSchema.Annotations(audience, 1.0);

        var fieldMapResource = new McpSchema.Resource(
                "employees://field-map",
                "Employee Field Map",
                "Defines all fields that can be used in employee filtering.",
                "text/markdown",
                annotations);

        String fieldMapContent = loadMarkdown("FieldMap.md");

        var fieldMapresourceSpec = new McpServerFeatures.SyncResourceSpecification(
                fieldMapResource,
                (exchange, request) -> {
                    return new McpSchema.ReadResourceResult(
                            List.of(
                                    new McpSchema.TextResourceContents(
                                            request.uri(),
                                            "text/markdown",
                                            fieldMapContent)));
                });

        var executeQueryResource = new McpSchema.Resource(
                "employees://execute-query-instructions",
                "Instructions for Employee Query Execution",
                "Execute QueryDSL query to filter employees.",
                "text/markdown",
                annotations);

        String executeQueryContent = loadMarkdown("ExecuteQuery.md");

        var executeQueryResourceSpec = new McpServerFeatures.SyncResourceSpecification(
                executeQueryResource,
                (exchange, request) -> {
                    return new McpSchema.ReadResourceResult(
                            List.of(
                                    new McpSchema.TextResourceContents(
                                            request.uri(),
                                            "text/markdown",
                                            executeQueryContent))); // Placeholder for actual JSON content
                });

        return List.of(fieldMapresourceSpec, executeQueryResourceSpec);
    }

    private String loadMarkdown(String path) {

        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(String.format("markdown/employee/%s", path))) {

            if (inputStream == null) {
                throw new RuntimeException(
                        String.format("Không tìm thấy file markdown/employee/%s", path));
            }

            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi đọc markdown: " + e.getMessage(), e);
        }

    }

}
