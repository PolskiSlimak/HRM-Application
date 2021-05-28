package com.paw.hrmApp.repository;

import com.paw.hrmApp.model.DepartmentEntity;
import com.paw.hrmApp.model.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Optional<DepartmentEntity> findByDepartmentName(String name);
}
