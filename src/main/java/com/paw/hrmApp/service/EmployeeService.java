package com.paw.hrmApp.service;

import com.paw.hrmApp.dao.FinderDAO;
import com.paw.hrmApp.dto.create.EmployeeCreateDTO;
import com.paw.hrmApp.dto.EmployeeDTO;
import com.paw.hrmApp.dto.create.EmployeeStatsDTO;
import com.paw.hrmApp.exception.ResourceNotFoundException;
import com.paw.hrmApp.mapper.EmployeeMapper;
import com.paw.hrmApp.model.DepartmentEntity;
import com.paw.hrmApp.model.EmployeeEntity;
import com.paw.hrmApp.model.EmployeeHistoryEntity;
import com.paw.hrmApp.model.JobEntity;
import com.paw.hrmApp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeHistoryRepository employeeHistoryRepository;
    private final FinderDAO finderDAO;

    public List<EmployeeDTO> getEmployees() {
        List<EmployeeEntity> employeeEntityList =  employeeRepository.findAll();
        return mapToDTOList(employeeEntityList);
    }

    public EmployeeDTO getParticularEmployee(Long id) {
        EmployeeEntity employeeEntity = finderDAO.getEmployeeEntity(id);
        return EmployeeMapper.mapToEmployeeDTO(employeeEntity);
    }

    public void deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = finderDAO.getEmployeeEntity(id);
        EmployeeHistoryEntity employeeHistoryEntity = EmployeeMapper.mapToEmployeeHistoryEntity(employeeEntity);
        employeeHistoryRepository.save(employeeHistoryEntity);
        employeeRepository.delete(employeeEntity);
    }

    public void editEmployee(EmployeeDTO employeeDTO) {
        Long id = employeeDTO.getEmployeeId();
        EmployeeEntity employeeEntity = finderDAO.getEmployeeEntity(id);
        EmployeeMapper.overrideEmployeeEntity(employeeEntity, employeeDTO);
        setDetailedInfo(employeeEntity, employeeDTO.getManagerId(), employeeDTO.getJobName(), employeeDTO.getDepartmentName());
        employeeRepository.save(employeeEntity);
    }

    public void createEmployee(EmployeeCreateDTO employeeCreateDTO) {
        EmployeeEntity employeeEntity = EmployeeMapper.mapSimpleTypesToEmployeeEntity(employeeCreateDTO);
        setDetailedInfo(employeeEntity, employeeCreateDTO.getManagerId(), employeeCreateDTO.getJobName(), employeeCreateDTO.getDepartmentName());
        employeeRepository.save(employeeEntity);
    }

    public EmployeeStatsDTO getStatistics(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateFrom;
        try {
            dateFrom = formatter.parse(date);
        } catch (ParseException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
        List<EmployeeEntity> employeeEntityList = employeeRepository.findEmployeesCount(dateFrom);
        Double averageSalary = getSalary(employeeEntityList);
        return EmployeeMapper.mapToStatsDTO(employeeEntityList.size(), averageSalary);
    }

    private List<EmployeeDTO> mapToDTOList(List<EmployeeEntity> employeeEntityList) {
        List<EmployeeDTO> employeesDTO = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntityList) {
            EmployeeDTO employeeDTO = EmployeeMapper.mapToEmployeeDTO(employeeEntity);
            employeesDTO.add(employeeDTO);
        }
        return employeesDTO;
    }

    private Double getSalary(List<EmployeeEntity> employeeEntityList) {
        Double salary = 0.0;
        for (EmployeeEntity employeeEntity: employeeEntityList) {
            salary = employeeEntity.getSalary() + salary;
        }
        if (salary == 0.0) {
            return 0.0;
        }
        return salary/employeeEntityList.size();
    }

    private void setDetailedInfo(EmployeeEntity employeeEntity, Long managerId, String jobName, String departmentName) {
        List<String> errors = new ArrayList<>();
        EmployeeEntity managerEntity = new EmployeeEntity();
        JobEntity jobEntity = new JobEntity();
        DepartmentEntity departmentEntity = new DepartmentEntity();
        try {
            managerEntity = finderDAO.getEmployeeEntity(managerId);
        } catch (ResourceNotFoundException e) {
            errors.add(e.getMessage());
        }
        try {
            jobEntity = finderDAO.getJobEntityByName(jobName);
        } catch (ResourceNotFoundException e) {
            errors.add(e.getMessage());
        }
        try {
            departmentEntity = finderDAO.getDepartmentEntityByName(departmentName);
        } catch (ResourceNotFoundException e){
            errors.add(e.getMessage());
        }

        if (!errors.isEmpty())
            throw new ResourceNotFoundException(errors);

        employeeEntity.setManagerEntity(managerEntity);
        employeeEntity.setJobEntity(jobEntity);
        employeeEntity.setDepartmentEntity(departmentEntity);
    }
}
