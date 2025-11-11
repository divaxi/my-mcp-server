package com.spring.ai.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.spring.ai.util.deserializer.QueryDeserializer;


@JsonDeserialize(using = QueryDeserializer.class)
public interface QueryClause {
    
}
