package com.paw.hrmApp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags="Job")
public class JobController {
    @ApiOperation(value = "Returns list of jobs")
    @GetMapping("/jobs")
    private void getJobs() {

    }

    @ApiOperation(value = "Returns specific job details")
    @GetMapping("/jobs/{id}")
    private void getSpecificJob(@ApiParam(value = "Id of job", example = "1") @PathVariable int id) {

    }

    @ApiOperation(value = "Returns job statistics")
    @GetMapping("/jobs/statistics")
    private void getStatistics() {

    }

    @ApiOperation(value = "Creates new job")
    @PostMapping("/jobs")
    private void createJob() {

    }

    @ApiOperation(value = "Deletes specific job")
    @DeleteMapping("/jobs")
    private void deleteJob(@ApiParam(value = "Id of job", example = "1") @PathVariable int id) {

    }
}
