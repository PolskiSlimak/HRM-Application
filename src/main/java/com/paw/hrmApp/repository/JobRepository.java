package com.paw.hrmApp.repository;

import com.paw.hrmApp.model.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {
    Optional<JobEntity> findByJobName(String name);

    @Query(value = "  SELECT j.job_name as jobName, count(*) as jobCount, avg(salary) as averageSalary FROM employee e " +
            "JOIN job j ON j.job_id = e.job_id GROUP BY j.job_name;", nativeQuery = true)
    List<Map<String, Object>> findJobStats();
}
