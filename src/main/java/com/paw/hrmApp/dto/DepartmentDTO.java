package com.paw.hrmApp.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DepartmentDTO {
    private String departmentName;
    private Long managerId;
    private Long locationId;
}
