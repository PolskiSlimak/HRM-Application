package com.paw.hrmApp.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
public class DepartmentStatsDTO {
    private HashMap<String, Integer> statisticsOfEmployment;
}
