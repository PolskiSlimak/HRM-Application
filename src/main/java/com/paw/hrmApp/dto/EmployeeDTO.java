package com.paw.hrmApp.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private Date hireDate;
    private String email;
    private String phoneNumber;
    private Double salary;
    private Long managerId;
    private String jobName;
    private String departmentName;
}
