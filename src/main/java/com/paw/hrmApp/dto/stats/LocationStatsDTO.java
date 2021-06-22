package com.paw.hrmApp.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LocationStatsDTO {
    private String cityName;
    private Integer departmentCount;
}
