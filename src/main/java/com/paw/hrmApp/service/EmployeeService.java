package com.paw.hrmApp.service;

import com.paw.hrmApp.model.DepartmentEntity;
import com.paw.hrmApp.model.EmployeeEntity;
import com.paw.hrmApp.model.JobEntity;
import com.paw.hrmApp.model.LocationEntity;
import com.paw.hrmApp.repository.DepartmentRepository;
import com.paw.hrmApp.repository.EmployeeRepository;
import com.paw.hrmApp.repository.JobRepository;
import com.paw.hrmApp.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final LocationRepository locationRepository;
    private final JobRepository jobRepository;
    private final DepartmentRepository departmentRepository;

    public void saveEmployee() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName("Damian");
        employeeEntity.setLastName("Nowak");
        employeeEntity.setHireDate(new Date());
        employeeEntity.setSalary(10000.00);
        employeeEntity.setPhoneNumber("234567543");
        employeeEntity.setEmail("damian.nowak@gmail.com");
        employeeEntity.setManagerEntity(employeeRepository.findById(1L).get());
        employeeEntity.setJobEntity(jobRepository.findById(1L).get());
        employeeEntity.setDepartmentEntity(departmentRepository.findById(1L).get());

        employeeRepository.save(employeeEntity);
    }

    public void saveLocation() {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCountryName("Poland");
        locationEntity.setCityName("Katowice");
        locationEntity.setPostalCode("40-000");
        locationEntity.setStreetAddress("Kwiatowa 50");

        locationRepository.save(locationEntity);
    }

    public void saveDepartment() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentName("HR");
        departmentEntity.setManagerEntity(employeeRepository.findById(1L).get());
        departmentEntity.setLocationEntity(locationRepository.findById(1L).get());

        departmentRepository.save(departmentEntity);
    }

    public void saveJob() {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobName("CEO");
        jobEntity.setMinSalary(10000.00);
        jobEntity.setMaxSalary(99999.00);

        jobRepository.save(jobEntity);
    }
}
