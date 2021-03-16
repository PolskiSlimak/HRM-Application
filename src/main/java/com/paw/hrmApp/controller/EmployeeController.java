package com.paw.hrmApp.controller;

import com.paw.hrmApp.configuration.SpringFoxConfig;
import com.paw.hrmApp.dto.EmployeeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = { SpringFoxConfig.employee })
public class EmployeeController {
    @ApiOperation(value = "Returns list of all employees")
    @GetMapping("/employees")
    private EmployeeDTO getEmployees() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        return employeeDTO;
    }

    @ApiOperation(value = "Returns data of specific employee")
    @GetMapping("/employees/{id}")
    private EmployeeDTO getSpecificEmployee(@ApiParam(value = "Id of employee", example = "1") @PathVariable Long id) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(id);
        return employeeDTO;
    }

    @ApiOperation(value = "Returns employment statistics from specific month")
    @GetMapping("/employees/statistics/{month}")
    private Map<String, Integer> getStatistics(@ApiParam(value = "Month in which statistics should be made", example = "July") @PathVariable String month) {
        return new HashMap<>();
    }

    @ApiOperation(value = "Delete specific employee")
    @DeleteMapping("/employees/{id}")
    private void deleteEmployee(@ApiParam(value = "Id of employee", example = "1") @PathVariable Long id) {

    }

    @ApiOperation(value = "Creates specific employee")
    @PostMapping("/employees")
    private void createEmployee() {

    }
}
