package com.paw.hrmApp.service;

import com.paw.hrmApp.dto.JobDTO;
import com.paw.hrmApp.dto.JobStatsDTO;
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

    public List<JobDTO> getJobs() {
        List<JobEntity> jobEntityList = jobRepository.findAll();
        return mapToDTOList(jobEntityList);
    }

    public JobDTO getParticularJob(Long id) {
        JobEntity jobEntity = jobRepository.findById(id).get();
        return JobMapper.mapToJobDTO(jobEntity);
    }

    public void saveJob(JobDTO jobDTO) {
        JobEntity jobEntity = JobMapper.mapToJobEntity(jobDTO);
        jobRepository.save(jobEntity);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
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
