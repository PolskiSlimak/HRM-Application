package com.paw.hrmApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JobCreateDTO {
    private String jobName;
    private Double minSalary;
    private Double maxSalary;
}
