package com.paw.hrmApp.dto;

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
