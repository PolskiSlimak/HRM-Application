package com.paw.hrmApp.mapper;

import com.paw.hrmApp.dto.JobDTO;
import com.paw.hrmApp.dto.stats.JobStatsDTO;
import com.paw.hrmApp.model.JobEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JobMapper {
    public static JobDTO mapToJobDTO(JobEntity jobEntity) {
        return JobDTO.builder()
                .jobId(jobEntity.getJobId())
                .jobName(jobEntity.getJobName())
                .maxSalary(jobEntity.getMaxSalary())
                .minSalary(jobEntity.getMinSalary())
                .build();
    }

    public static void overrideJobEntity(JobEntity jobEntity, String jobName, Double minSalary, Double maxSalary) {
        jobEntity.setJobName(jobName);
        jobEntity.setMinSalary(minSalary);
        jobEntity.setMaxSalary(maxSalary);
    }

    public static List<JobStatsDTO> mapToJobStatsDTOList(List<Map<String, Object>> stats) {
        List<JobStatsDTO> statsDTOList = new ArrayList<>();
        for (Map<String, Object> map : stats) {
            String jobName = (String) map.get("jobName");
            Integer jobCount = (Integer) map.get("jobCount");
            BigDecimal averageSalary = (BigDecimal) map.get("averageSalary");
            JobStatsDTO jobStatsDTO = new JobStatsDTO(jobName, jobCount, averageSalary);
            statsDTOList.add(jobStatsDTO);
        }
        return statsDTOList;
    }
}
