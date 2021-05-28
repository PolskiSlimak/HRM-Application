package com.paw.hrmApp.repository;

import com.paw.hrmApp.model.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {
    Optional<JobEntity> findByJobName(String name);
}
