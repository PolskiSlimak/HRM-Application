package com.paw.hrmApp.controller;

import com.paw.hrmApp.configuration.SpringFoxConfig;
import com.paw.hrmApp.model.JobEntity;
import com.paw.hrmApp.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = { SpringFoxConfig.job })
public class JobController {
    private final EmployeeService employeeService;
    @ApiOperation(value = "Returns list of jobs")
    @GetMapping("/jobs")
    private JobEntity getJobs() {
        JobEntity jobEntity = new JobEntity();
        return jobEntity;
    }

    @ApiOperation(value = "Returns specific job details")
    @GetMapping("/jobs/{id}")
    private JobEntity getSpecificJob(@ApiParam(value = "Id of job", example = "1", required = true) @PathVariable Long id) {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobId(id);
        return jobEntity;
    }

    @ApiOperation(value = "Returns job statistics")
    @GetMapping("/jobs/statistics")
    private Map<String, Integer> getStatistics() {
        return new HashMap<>();
    }

    @ApiOperation(value = "Creates new job")
    @PostMapping("/jobs")
    private void createJob() {
        employeeService.saveJob();
    }

    @ApiOperation(value = "Deletes specific job")
    @DeleteMapping("/jobs/{id}")
    private void deleteJob(@ApiParam(value = "Id of job", example = "1", required = true) @PathVariable Long id) {

    }
}
