package com.spring.ai.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/*
 * An extensible query request structure that encapsulates filtering criteria and pagination options.
 * An example of request payload:
 * 
 * {
 *   "where": {
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
 *   },
 *   "limit": 5,
 *   "page": 0
 * }
 */

@Getter
@Setter
@Builder
public class QueryRequest<T> {

    private  Query<T> where;

    @Builder.Default
    private Integer limit = 5;
    @Builder.Default
    private Integer page =1;
    
}
