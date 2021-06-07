package com.paw.hrmApp.mapper;

import com.paw.hrmApp.dto.DepartmentDTO;
import com.paw.hrmApp.dto.DepartmentStatsDTO;
import com.paw.hrmApp.model.DepartmentEntity;
import com.paw.hrmApp.model.EmployeeEntity;
import com.paw.hrmApp.model.LocationEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentMapper {
    public static DepartmentDTO mapToDepartmentDTO(DepartmentEntity departmentEntity) {
        return DepartmentDTO.builder()
                .departmentId(departmentEntity.getDepartmentId())
                .departmentName(departmentEntity.getDepartmentName())
                .managerId(departmentEntity.getManagerEntity().getEmployeeId())
                .locationId(departmentEntity.getLocationEntity().getLocationId())
                .build();
    }

    public static void mapToDepartmentEntity(DepartmentEntity departmentEntity, String departmentName, EmployeeEntity managerEntity, LocationEntity locationEntity) {
        departmentEntity.setDepartmentName(departmentName);
        departmentEntity.setManagerEntity(managerEntity);
        departmentEntity.setLocationEntity(locationEntity);
    }

    public static DepartmentStatsDTO mapToStatsDTO(List<Map<String, Object>> stats) {
        HashMap<String, Integer> statisticsMap = new HashMap<>();
        for (Map<String, Object> map : stats) {
            String departmentName = (String) map.get("departmentName");
            Integer employeeCount = (Integer) map.get("employeeCount");
            statisticsMap.put(departmentName, employeeCount);
        }
        return DepartmentStatsDTO.builder()
                .statisticsOfEmployment(statisticsMap)
                .build();
    }
}
