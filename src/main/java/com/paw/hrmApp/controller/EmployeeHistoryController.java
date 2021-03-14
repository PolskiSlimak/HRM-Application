package com.paw.hrmApp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags="EmployeeHistory")
public class EmployeeHistoryController {
    @ApiOperation(value = "Returns list of all archived employees")
    @GetMapping("/employee-history")
    private void getEmployeeHistory() {

    }

    @ApiOperation(value = "Returns specific archived employee")
    @GetMapping("/employee-history/{id}")
    private void getSpecificEmployeeHistory(@ApiParam(value = "Id of archived employee", example = "1") @PathVariable int id) {

    }

    @ApiOperation(value = "Returns statistics from specific month")
    @GetMapping("/employee-history/statistic/{month}")
    private void getStatistics(@ApiParam(value = "Month in which statistics should be made", example = "July") @PathVariable String month) {

    }

    @ApiOperation(value = "Delete specific employee from history")
    @DeleteMapping("/employee-history/{id}")
    private void deleteEmployeeHistory(@ApiParam(value = "Id of archived employees", example = "1") @PathVariable int id) {

    }
}
