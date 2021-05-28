package com.paw.hrmApp.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class EmployeeHistoryDTO {
    private Long employeeId;
    private Date startDate;
    private Date endDate;
    private String jobName;
    private String departmentName;
}
