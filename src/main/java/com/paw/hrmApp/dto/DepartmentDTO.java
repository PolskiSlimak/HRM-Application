package com.paw.hrmApp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentDTO {
    private Long departmentId;
    private String departmentName;
    private ManagerDTO managerInfo;
    private LocationDTO locationInfo;
}
