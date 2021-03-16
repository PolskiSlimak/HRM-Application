package com.paw.hrmApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class EmployeeHistoryDTO {
    private Long employeeId;
    private Date startDate;
    private Date endDate;
    private String jobName;
    private Long departmentId;
    private String departmentName;
}
