package com.paw.hrmApp.repository;

import com.paw.hrmApp.model.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Optional<DepartmentEntity> findByDepartmentName(String name);

    @Query(value = "SELECT d.department_name as departmentName, count(*) as employeeCount  FROM employee e " +
            "JOIN department d ON d.department_id = e.department_id GROUP BY d.department_name ", nativeQuery = true)
    List<Map<String, Object>> findDepartmentStats();
}
