package com.paw.hrmApp.service;

import com.paw.hrmApp.dto.DepartmentCreateDTO;
import com.paw.hrmApp.dto.DepartmentDTO;
import com.paw.hrmApp.dto.DepartmentStatsDTO;
import com.paw.hrmApp.mapper.DepartmentMapper;
import com.paw.hrmApp.model.DepartmentEntity;
import com.paw.hrmApp.model.EmployeeEntity;
import com.paw.hrmApp.model.LocationEntity;
import com.paw.hrmApp.repository.DepartmentRepository;
import com.paw.hrmApp.repository.EmployeeRepository;
import com.paw.hrmApp.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final LocationRepository locationRepository;

    public List<DepartmentDTO> getDepartments() {
        List<DepartmentEntity> departmentEntityList = departmentRepository.findAll();
        return mapToDTOList(departmentEntityList);
    }

    public DepartmentDTO getParticularDepartment(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).get();
        return DepartmentMapper.mapToDepartmentDTO(departmentEntity);
    }

    public void editDepartment(DepartmentDTO departmentDTO) {
        Long id = departmentDTO.getDepartmentId();
        DepartmentEntity departmentEntity = departmentRepository.findById(id).get();
        setDetailedInfo(departmentEntity, departmentDTO.getDepartmentName(), departmentDTO.getManagerId(), departmentDTO.getLocationId());
        departmentRepository.save(departmentEntity);
    }

    public void createDepartment(DepartmentCreateDTO departmentCreateDTO) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        setDetailedInfo(departmentEntity, departmentCreateDTO.getDepartmentName(), departmentCreateDTO.getManagerId(), departmentCreateDTO.getLocationId());
        departmentRepository.save(departmentEntity);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public DepartmentStatsDTO getStatistics() {
        List<Map<String, Object>> stats = departmentRepository.findDepartmentStats();
        return DepartmentMapper.mapToStatsDTO(stats);
    }

    private List<DepartmentDTO> mapToDTOList(List<DepartmentEntity> departmentEntityList) {
        List<DepartmentDTO> departmentsDTO = new ArrayList<>();
        for (DepartmentEntity departmentEntity : departmentEntityList) {
            DepartmentDTO departmentDTO = DepartmentMapper.mapToDepartmentDTO(departmentEntity);
            departmentsDTO.add(departmentDTO);
        }
        return departmentsDTO;
    }

    private void setDetailedInfo(DepartmentEntity departmentEntity, String name, Long managerId, Long locationId) {
        EmployeeEntity managerEntity = employeeRepository.findById(managerId).get();
        LocationEntity locationEntity = locationRepository.findById(locationId).get();
        DepartmentMapper.mapToDepartmentEntity(departmentEntity, name, managerEntity, locationEntity);
    }
}
