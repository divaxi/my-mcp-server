package com.spring.ai.dto;

import com.spring.ai.dto.Employee.EmployeeResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChunk {

    public String message;

    public PagingList<EmployeeResponse> result;
    
}
