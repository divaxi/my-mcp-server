package com.spring.ai.dto;

import java.util.List;

import com.spring.ai.utils.ConditionOperator;

import jakarta.persistence.criteria.Predicate.BooleanOperator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/*
 * Represents a query condition or a group of conditions for filtering data.
 * It can represent either a single condition (field, operator, value) or a composite condition
 * that combines multiple subconditions using a logical operator (AND/OR).
 * 
 * Example of a composite condition:
 * {
 *     "operator": "AND",
 *     "conditions": [
 *       {
 *         "field": "jobTitle",
 *         "op": "LIKE",
 *         "value": "programmer"
 *       },
 *       {
 *         "field": "salary",
 *         "op": "GREATER_THAN",
 *         "value": "5000"
 *       }
 *     ]
 *   }
 */

@Getter
@Setter
@Builder
public class Query<T> {

    // logical operator (AND/OR)
    private BooleanOperator operator;

    // subconditions
    private List<Query<T>> conditions;

    // single condition
    private String field;
    private ConditionOperator op;
    private Object value;
}

