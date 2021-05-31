package com.paw.hrmApp.mapper;

import com.paw.hrmApp.dto.EmployeeDTO;
import com.paw.hrmApp.dto.EmployeeStatsDTO;
import com.paw.hrmApp.model.EmployeeEntity;
import com.paw.hrmApp.model.EmployeeHistoryEntity;

import java.util.Date;

public class EmployeeMapper {
    public static EmployeeDTO mapToEmployeeDTO(EmployeeEntity employeeEntity) {
        return EmployeeDTO.builder()
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .hireDate(employeeEntity.getHireDate())
                .departmentName(employeeEntity.getDepartmentEntity().getDepartmentName())
                .jobName(employeeEntity.getJobEntity().getJobName())
                .managerId(employeeEntity.getManagerEntity().getEmployeeId())
                .build();
    }

    public static EmployeeEntity mapSimpleTypesToEmployeeEntity(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employeeDTO.getFirstName());
        employeeEntity.setLastName(employeeDTO.getLastName());
        employeeEntity.setHireDate(employeeDTO.getHireDate());
        employeeEntity.setEmail(employeeDTO.getEmail());
        employeeEntity.setPhoneNumber(employeeDTO.getPhoneNumber());
        employeeEntity.setSalary(employeeDTO.getSalary());
        return employeeEntity;
    }

    public static EmployeeHistoryEntity mapToEmployeeHistoryEntity(EmployeeEntity employeeEntity) {
        EmployeeHistoryEntity employeeHistoryEntity = new EmployeeHistoryEntity();
        employeeHistoryEntity.setStartDate(employeeEntity.getHireDate());
        employeeHistoryEntity.setEndDate(new Date());
        employeeHistoryEntity.setDepartmentEntity(employeeEntity.getDepartmentEntity());
        employeeHistoryEntity.setJobEntity(employeeEntity.getJobEntity());
        employeeHistoryEntity.setEmployeeEntity(employeeEntity);
        return employeeHistoryEntity;
    }

    public static EmployeeStatsDTO mapToStatsDTO(Integer size, Double salary) {
        return EmployeeStatsDTO.builder()
                .employeeCount(size)
                .averageSalary(salary)
                .build();
    }
}
