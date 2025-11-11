package com.spring.ai.dto;

import java.util.Map;

import lombok.Data;

@Data
public class RangeQuery implements QueryClause {
    private Map<String, Object> range;
    
}
