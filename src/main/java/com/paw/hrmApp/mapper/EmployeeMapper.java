package com.paw.hrmApp.mapper;

import com.paw.hrmApp.dto.create.EmployeeCreateDTO;
import com.paw.hrmApp.dto.EmployeeDTO;
import com.paw.hrmApp.dto.EmployeeHistoryDTO;
import com.paw.hrmApp.dto.create.EmployeeStatsDTO;
import com.paw.hrmApp.model.EmployeeEntity;
import com.paw.hrmApp.model.EmployeeHistoryEntity;

import java.time.LocalDate;

public class EmployeeMapper {
    public static EmployeeDTO mapToEmployeeDTO(EmployeeEntity employeeEntity) {
        Long id = null;
        if (employeeEntity.getManagerEntity() != null)
            id = employeeEntity.getManagerEntity().getEmployeeId();
        return EmployeeDTO.builder()
                .employeeId(employeeEntity.getEmployeeId())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .hireDate(employeeEntity.getHireDate())
                .email(employeeEntity.getEmail())
                .phoneNumber(employeeEntity.getPhoneNumber())
                .salary(employeeEntity.getSalary())
                .departmentName(employeeEntity.getDepartmentEntity().getDepartmentName())
                .jobName(employeeEntity.getJobEntity().getJobName())
                .managerId(id)
                .build();
    }

    public static EmployeeEntity mapSimpleTypesToEmployeeEntity(EmployeeCreateDTO employeeDTO) {
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
        employeeHistoryEntity.setEndDate(LocalDate.now());
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

    public static EmployeeHistoryDTO mapToEmployeeHistoryDTO(EmployeeHistoryEntity employeeEntity) {
        return EmployeeHistoryDTO.builder()
                .employeeHistoryId(employeeEntity.getEmployeeHistoryId())
                .startDate(employeeEntity.getStartDate())
                .endDate(employeeEntity.getEndDate())
                .jobName(employeeEntity.getJobEntity().getJobName())
                .departmentName(employeeEntity.getDepartmentEntity().getDepartmentName())
                .build();
    }

    public static void overrideEmployeeEntity(EmployeeEntity employeeEntity, EmployeeDTO employeeDTO) {
        employeeEntity.setFirstName(employeeDTO.getFirstName());
        employeeEntity.setLastName(employeeDTO.getLastName());
        employeeEntity.setHireDate(employeeDTO.getHireDate());
        employeeEntity.setEmail(employeeDTO.getEmail());
        employeeEntity.setPhoneNumber(employeeDTO.getPhoneNumber());
        employeeEntity.setSalary(employeeDTO.getSalary());
    }
}
