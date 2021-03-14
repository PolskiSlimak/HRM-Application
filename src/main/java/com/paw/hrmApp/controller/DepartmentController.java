package com.paw.hrmApp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags="Department")
public class DepartmentController {
    @ApiOperation(value = "Returns list of all departments")
    @GetMapping("/departments")
    private void getDepartment() {

    }

    @ApiOperation(value = "Returns specific department")
    @GetMapping("/departments/{id}")
    private void getSpecificDepartment(@ApiParam(value = "Id of department", example = "1") @PathVariable int id) {

    }

    @ApiOperation(value = "Returns how many employees work in each department")
    @GetMapping("/departments/statistic")
    private void getStatistics() {

    }

    @ApiOperation(value = "Creates specific department")
    @PostMapping("/departments")
    private void createDepartment() {

    }

    @ApiOperation(value = "Delete specific department")
    @DeleteMapping("/departments/{id}")
    private void deleteDepartment(@ApiParam(value = "Id of department", example = "1") @PathVariable int id) {

    }
}
