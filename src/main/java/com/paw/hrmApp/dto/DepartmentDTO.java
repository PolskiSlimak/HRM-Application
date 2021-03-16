package com.paw.hrmApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class DepartmentDTO {
    private Long departmentId;
    private String departmentName;
    private List<ManagerDTO> managerInfo;
    private List<LocationDTO> locationInfo;
}
