package com.paw.hrmApp.controller;

import com.paw.hrmApp.configuration.SpringFoxConfig;
import com.paw.hrmApp.dto.JobCreateDTO;
import com.paw.hrmApp.dto.JobDTO;
import com.paw.hrmApp.dto.JobStatsDTO;
import com.paw.hrmApp.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = { SpringFoxConfig.job })
public class JobController {
    private final JobService jobService;

    @ApiOperation(value = "Returns list of jobs")
    @GetMapping("/jobs")
    private List<JobDTO> getJobs() {
        return jobService.getJobs();
    }

    @ApiOperation(value = "Returns specific job details")
    @GetMapping("/jobs/{id}")
    private JobDTO getSpecificJob(@ApiParam(value = "Id of job", example = "1", required = true) @PathVariable Long id) {
        return jobService.getParticularJob(id);
    }

    @ApiOperation(value = "Returns job statistics")
    @GetMapping("/jobs/statistics")
    private List<JobStatsDTO> getStatistics() {
        return jobService.getStatistics();
    }

    @ApiOperation(value = "Edit particular job")
    @PutMapping("/jobs")
    private void editJob(@RequestBody JobDTO jobDTO) {
        jobService.editJob(jobDTO);
    }

    @ApiOperation(value = "Create new job")
    @PostMapping("/jobs")
    private void createJob(@RequestBody JobCreateDTO jobDTO) {
        jobService.createJob(jobDTO);
    }

    @ApiOperation(value = "Deletes specific job")
    @DeleteMapping("/jobs/{id}")
    private void deleteJob(@ApiParam(value = "Id of job", example = "1", required = true) @PathVariable Long id) {
        jobService.deleteJob(id);
    }
}
