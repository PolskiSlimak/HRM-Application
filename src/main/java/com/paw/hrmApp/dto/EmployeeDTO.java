package com.paw.hrmApp.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class EmployeeDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate hireDate;
    private String email;
    private String phoneNumber;
    private Double salary;
    private Long managerId;
    private String jobName;
    private String departmentName;
}
