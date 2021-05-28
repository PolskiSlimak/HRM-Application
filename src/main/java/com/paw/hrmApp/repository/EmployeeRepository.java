package com.paw.hrmApp.repository;

import com.paw.hrmApp.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query(value = "SELECT * FROM employee e WHERE e.hire_date >= ?1", nativeQuery = true)
    List<EmployeeEntity> findEmployeesCount(Date date);
}
