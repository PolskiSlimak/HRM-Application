package com.paw.hrmApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class EmployeeDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String jobName;
    private String departmentName;
    private Date hireDate;
    private List<ManagerDTO> managerInfo;
}
