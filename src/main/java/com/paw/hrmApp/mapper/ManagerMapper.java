package com.paw.hrmApp.mapper;

import com.paw.hrmApp.dto.ManagerDTO;
import com.paw.hrmApp.model.EmployeeEntity;

public class ManagerMapper {
    public static ManagerDTO mapToManagerDTO(EmployeeEntity employeeEntity) {
        return ManagerDTO.builder()
                .managerId(employeeEntity.getEmployeeId())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .build();
    }
}
