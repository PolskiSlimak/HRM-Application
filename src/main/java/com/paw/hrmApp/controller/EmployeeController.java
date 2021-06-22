package com.paw.hrmApp.controller;

import com.paw.hrmApp.configuration.SpringFoxConfig;
import com.paw.hrmApp.dto.create.EmployeeCreateDTO;
import com.paw.hrmApp.dto.EmployeeDTO;
import com.paw.hrmApp.dto.create.EmployeeStatsDTO;
import com.paw.hrmApp.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = { SpringFoxConfig.employee })
public class EmployeeController {
    private final EmployeeService employeeService;

    @ApiOperation(value = "Returns list of all employees")
    @GetMapping("/employees")
    private List<EmployeeDTO> getEmployees() {
        return employeeService.getEmployees();
    }

    @ApiOperation(value = "Returns data of specific employee")
    @GetMapping("/employees/{id}")
    private EmployeeDTO getSpecificEmployee(@ApiParam(value = "Id of employee", example = "1", required = true) @PathVariable Long id) {
        return employeeService.getParticularEmployee(id);
    }

    @ApiOperation(value = "Returns employment statistics")
    @GetMapping("/employees/statistics/{date}")
    private EmployeeStatsDTO getStatistics(@ApiParam(value = "Date from which statistics should be made", example = "30-03-2021", required = true) @PathVariable String date) throws ParseException {
        return employeeService.getStatistics(date);
    }

    @ApiOperation(value = "Delete specific employee")
    @DeleteMapping("/employees/{id}")
    private void deleteEmployee(@ApiParam(value = "Id of employee", example = "1", required = true) @PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @ApiOperation(value = "Edit specific employee")
    @PutMapping("/employee")
    private void editEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.editEmployee(employeeDTO);
    }

    @ApiOperation(value = "Create employee")
    @PostMapping("/employee")
    private void createEmployee(@RequestBody EmployeeCreateDTO employeeCreateDTO) {
        employeeService.createEmployee(employeeCreateDTO);
    }
}
