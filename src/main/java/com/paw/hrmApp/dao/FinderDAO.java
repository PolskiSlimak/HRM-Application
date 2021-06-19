package com.paw.hrmApp.dao;

import com.paw.hrmApp.exception.ResourceNotFoundException;
import com.paw.hrmApp.model.*;
import com.paw.hrmApp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinderDAO {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final LocationRepository locationRepository;
    private final EmployeeHistoryRepository employeeHistoryRepository;
    private final JobRepository jobRepository;

    public DepartmentEntity getDepartmentEntity(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find specified department"));
    }

    public DepartmentEntity getDepartmentEntityByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find specified department"));
    }

    public EmployeeEntity getEmployeeEntity(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find specified employee"));
    }

    public LocationEntity getLocationEntity(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find specified location"));
    }

    public EmployeeHistoryEntity getHistoryEntity(Long id) {
        return employeeHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find specified archived employee"));
    }

    public JobEntity getJobEntityByName(String jobName) {
        return jobRepository.findByJobName(jobName)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find specified job"));
    }

    public JobEntity getJobEntity(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find specified job"));
    }
}
