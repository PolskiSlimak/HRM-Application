package com.paw.hrmApp.service;

import com.paw.hrmApp.dao.FinderDAO;
import com.paw.hrmApp.dto.JobCreateDTO;
import com.paw.hrmApp.dto.JobDTO;
import com.paw.hrmApp.dto.stats.JobStatsDTO;
import com.paw.hrmApp.mapper.JobMapper;
import com.paw.hrmApp.model.JobEntity;
import com.paw.hrmApp.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final FinderDAO finderDAO;

    public List<JobDTO> getJobs() {
        List<JobEntity> jobEntityList = jobRepository.findAll();
        return mapToDTOList(jobEntityList);
    }

    public JobDTO getParticularJob(Long id) {
        JobEntity jobEntity = finderDAO.getJobEntity(id);
        return JobMapper.mapToJobDTO(jobEntity);
    }

    public void editJob(JobDTO jobDTO) {
        Long id = jobDTO.getJobId();
        JobEntity jobEntity = finderDAO.getJobEntity(id);
        JobMapper.overrideJobEntity(jobEntity, jobDTO.getJobName(), jobDTO.getMinSalary(), jobDTO.getMaxSalary());
        jobRepository.save(jobEntity);
    }

    public void createJob(JobCreateDTO jobCreateDTO) {
        JobEntity jobEntity = new JobEntity();
        JobMapper.overrideJobEntity(jobEntity, jobCreateDTO.getJobName(), jobCreateDTO.getMinSalary(), jobCreateDTO.getMaxSalary());
        jobRepository.save(jobEntity);
    }

    public void deleteJob(Long id) {
        JobEntity jobEntity = finderDAO.getJobEntity(id);
        jobRepository.delete(jobEntity);
    }

    public List<JobStatsDTO> getStatistics() {
        List<Map<String, Object>> stats = jobRepository.findJobStats();
        return JobMapper.mapToJobStatsDTOList(stats);
    }

    private List<JobDTO> mapToDTOList(List<JobEntity> jobEntityList) {
        List<JobDTO> jobDTOList = new ArrayList<>();
        for (JobEntity jobEntity : jobEntityList) {
            JobDTO jobDTO = JobMapper.mapToJobDTO(jobEntity);
            jobDTOList.add(jobDTO);
        }
        return jobDTOList;
    }
}
