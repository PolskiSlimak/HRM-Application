package com.paw.hrmApp.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DepartmentCreateDTO {
    private String departmentName;
    private Long managerId;
    private Long locationId;
}
