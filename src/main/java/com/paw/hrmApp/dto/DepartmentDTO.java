package com.paw.hrmApp.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DepartmentDTO {
    private Long departmentId;
    private String departmentName;
    private ManagerDTO managerInfo;
    private LocationDTO locationInfo;
}
