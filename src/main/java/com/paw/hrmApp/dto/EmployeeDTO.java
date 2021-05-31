package com.paw.hrmApp.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate hireDate;
    private String email;
    private String phoneNumber;
    private Double salary;
    private Long managerId;
    private String jobName;
    private String departmentName;
}
