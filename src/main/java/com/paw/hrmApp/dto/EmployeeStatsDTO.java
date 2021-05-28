package com.paw.hrmApp.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class EmployeeStatsDTO {
    private Integer employeeCount;
    private Double averageSalary;
}
