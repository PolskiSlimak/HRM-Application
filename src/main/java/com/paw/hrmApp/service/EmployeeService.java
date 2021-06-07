package com.paw.hrmApp.service;

import com.paw.hrmApp.dto.EmployeeCreateDTO;
import com.paw.hrmApp.dto.EmployeeDTO;
import com.paw.hrmApp.dto.EmployeeStatsDTO;
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
    private final JobRepository jobRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeHistoryRepository employeeHistoryRepository;

    public List<EmployeeDTO> getEmployees() {
        List<EmployeeEntity> employeeEntityList =  employeeRepository.findAll();
        return mapToDTOList(employeeEntityList);
    }

    public EmployeeDTO getParticularEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        return EmployeeMapper.mapToEmployeeDTO(employeeEntity);
    }

    public void deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        EmployeeHistoryEntity employeeHistoryEntity = EmployeeMapper.mapToEmployeeHistoryEntity(employeeEntity);
        employeeHistoryRepository.save(employeeHistoryEntity);
        employeeRepository.delete(employeeEntity);
    }

    public void editEmployee(EmployeeDTO employeeDTO) {
        Long id = employeeDTO.getEmployeeId();
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
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
        Date dateFrom = formatter.parse(date);
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
        EmployeeEntity managerEntity = employeeRepository.findById(managerId).get();
        employeeEntity.setManagerEntity(managerEntity);
        JobEntity jobEntity = jobRepository.findByJobName(jobName).get();
        employeeEntity.setJobEntity(jobEntity);
        DepartmentEntity departmentEntity = departmentRepository.findByDepartmentName(departmentName).get();
        employeeEntity.setDepartmentEntity(departmentEntity);
    }
}
