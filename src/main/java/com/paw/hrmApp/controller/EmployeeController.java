package com.paw.hrmApp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags="Employee")
public class EmployeeController {
    @ApiOperation(value = "Returns list of all employees")
    @GetMapping("/employees")
    private void getEmployees() {

    }

    @ApiOperation(value = "Returns data of specific employee")
    @GetMapping("/employees/{id}")
    private void getSpecificEmployee(@ApiParam(value = "Id of employee", example = "1") @PathVariable int id) {

    }

    @ApiOperation(value = "Returns employment statistics from specific month")
    @GetMapping("/employees/statistics/{month}")
    private void getStatistics(@ApiParam(value = "Month in which statistics should be made", example = "July") @PathVariable String month) {

    }

    @ApiOperation(value = "Delete specific employee")
    @DeleteMapping("/employees/{id}")
    private void deleteEmployee(@ApiParam(value = "Id of employee", example = "1") @PathVariable int id) {

    }

    @ApiOperation(value = "Creates specific employee")
    @PostMapping("/employees")
    private void createEmployee() {

    }
}
