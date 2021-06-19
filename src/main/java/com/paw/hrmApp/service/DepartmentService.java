package com.paw.hrmApp.service;

import com.paw.hrmApp.dao.FinderDAO;
import com.paw.hrmApp.dto.DepartmentCreateDTO;
import com.paw.hrmApp.dto.DepartmentDTO;
import com.paw.hrmApp.dto.DepartmentStatsDTO;
import com.paw.hrmApp.exception.ResourceNotFoundException;
import com.paw.hrmApp.mapper.DepartmentMapper;
import com.paw.hrmApp.model.DepartmentEntity;
import com.paw.hrmApp.model.EmployeeEntity;
import com.paw.hrmApp.model.LocationEntity;
import com.paw.hrmApp.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final FinderDAO finderDAO;

    public List<DepartmentDTO> getDepartments() {
        List<DepartmentEntity> departmentEntityList = departmentRepository.findAll();
        return mapToDTOList(departmentEntityList);
    }

    public DepartmentDTO getParticularDepartment(Long id) {
        DepartmentEntity departmentEntity = finderDAO.getDepartmentEntity(id);
        return DepartmentMapper.mapToDepartmentDTO(departmentEntity);
    }

    public void editDepartment(DepartmentDTO departmentDTO) {
        Long id = departmentDTO.getDepartmentId();
        DepartmentEntity departmentEntity = finderDAO.getDepartmentEntity(id);
        setDetailedInfo(departmentEntity, departmentDTO.getDepartmentName(), departmentDTO.getManagerId(), departmentDTO.getLocationId());
        departmentRepository.save(departmentEntity);
    }

    public void createDepartment(DepartmentCreateDTO departmentCreateDTO) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        setDetailedInfo(departmentEntity, departmentCreateDTO.getDepartmentName(), departmentCreateDTO.getManagerId(), departmentCreateDTO.getLocationId());
        departmentRepository.save(departmentEntity);
    }

    public void deleteDepartment(Long id) {
        DepartmentEntity departmentEntity = finderDAO.getDepartmentEntity(id);
        departmentRepository.delete(departmentEntity);
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
        List<String> errors = new ArrayList<>();
        EmployeeEntity managerEntity = new EmployeeEntity();
        LocationEntity locationEntity = new LocationEntity();
        try {
            managerEntity = finderDAO.getEmployeeEntity(managerId);
        } catch (ResourceNotFoundException e) {
            errors.add(e.getMessage());
        }
        try {
            locationEntity = finderDAO.getLocationEntity(locationId);
        } catch (ResourceNotFoundException e) {
            errors.add(e.getMessage());
        }
        if (!errors.isEmpty())
            throw new ResourceNotFoundException(errors);

        DepartmentMapper.mapToDepartmentEntity(departmentEntity, name, managerEntity, locationEntity);
    }
}
