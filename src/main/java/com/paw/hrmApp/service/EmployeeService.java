package com.paw.hrmApp.service;

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

    public void saveEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = EmployeeMapper.mapSimpleTypesToEmployeeEntity(employeeDTO);
        EmployeeEntity managerEntity = employeeRepository.findById(employeeDTO.getManagerId()).get();
        employeeEntity.setManagerEntity(managerEntity);
        JobEntity jobEntity = jobRepository.findByJobName(employeeDTO.getJobName()).get();
        employeeEntity.setJobEntity(jobEntity);
        DepartmentEntity departmentEntity = departmentRepository.findByDepartmentName(employeeDTO.getDepartmentName()).get();
        employeeEntity.setDepartmentEntity(departmentEntity);
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

//    public void saveEmployee() {
//        EmployeeEntity employeeEntity = new EmployeeEntity();
//        employeeEntity.setFirstName("Damian");
//        employeeEntity.setLastName("Nowak");
//        employeeEntity.setHireDate(new Date());
//        employeeEntity.setSalary(10000.00);
//        employeeEntity.setPhoneNumber("234567543");
//        employeeEntity.setEmail("damian.nowak@gmail.com");
//        employeeEntity.setManagerEntity(employeeRepository.findById(1L).get());
//        employeeEntity.setJobEntity(jobRepository.findById(1L).get());
//        employeeEntity.setDepartmentEntity(departmentRepository.findById(1L).get());
//
//        employeeRepository.save(employeeEntity);
//    }
//
//    public void saveLocation() {
//        LocationEntity locationEntity = new LocationEntity();
//        locationEntity.setCountryName("Poland");
//        locationEntity.setCityName("Katowice");
//        locationEntity.setPostalCode("40-000");
//        locationEntity.setStreetAddress("Kwiatowa 50");
//
//        locationRepository.save(locationEntity);
//    }
//
//    public void saveDepartment() {
//        DepartmentEntity departmentEntity = new DepartmentEntity();
//        departmentEntity.setDepartmentName("HR");
//        departmentEntity.setManagerEntity(employeeRepository.findById(1L).get());
//        departmentEntity.setLocationEntity(locationRepository.findById(1L).get());
//
//        departmentRepository.save(departmentEntity);
//    }
//
//    public void saveJob() {
//        JobEntity jobEntity = new JobEntity();
//        jobEntity.setJobName("CEO");
//        jobEntity.setMinSalary(10000.00);
//        jobEntity.setMaxSalary(99999.00);
//
//        jobRepository.save(jobEntity);
//    }
}
