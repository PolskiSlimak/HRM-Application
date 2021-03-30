package com.paw.hrmApp.repository;

import com.paw.hrmApp.model.EmployeeHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeHistoryRepository extends JpaRepository<EmployeeHistoryEntity, Long> {
}
