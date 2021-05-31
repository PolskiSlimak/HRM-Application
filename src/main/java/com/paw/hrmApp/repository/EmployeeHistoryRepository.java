package com.paw.hrmApp.repository;

import com.paw.hrmApp.model.EmployeeHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EmployeeHistoryRepository extends JpaRepository<EmployeeHistoryEntity, Long> {
    @Query(value = "SELECT count(*) as numberOfEmployees FROM employee_history WHERE end_date >= ?1", nativeQuery = true)
    Integer findEmployeesCount(Date date);
}
