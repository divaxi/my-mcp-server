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
public class QueryResource {

    @Bean
    public List<McpServerFeatures.SyncResourceSpecification> QueryDSLResource() {

        List<McpSchema.Role> audience = List.of(McpSchema.Role.ASSISTANT);
        McpSchema.Annotations annotations = new McpSchema.Annotations(audience, 1.0);

        var fieldMapResource = new McpSchema.Resource(
                "query://query-dsl",
                "QueryDSL schema",
                "Document defines the JSON structure for sending QueryDSL-style search requests.",
                "text/markdown",
                annotations);

        String fieldMapContent = loadMarkdown("QueryDSLSchema.md");

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

        return List.of(fieldMapresourceSpec);
    }

    private String loadMarkdown(String path) {

        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(String.format("markdown/%s", path))) {

            if (inputStream == null) {
                throw new RuntimeException(
                        String.format("Không tìm thấy file markdown/%s", path));
            }

            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi đọc markdown: " + e.getMessage(), e);
        }

    }

}
