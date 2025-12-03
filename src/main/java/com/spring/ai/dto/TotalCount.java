package com.spring.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalCount implements RCResult {

    public long total;
    
}
