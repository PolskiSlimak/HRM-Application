package com.paw.hrmApp.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class JobStatsDTO {
    private String jobName;
    private Integer jobCount;
    private BigDecimal averageSalaryForJob;
}
