package com.paw.hrmApp.controller;

import com.paw.hrmApp.configuration.SpringFoxConfig;
import com.paw.hrmApp.dto.EmployeeHistoryDTO;
import com.paw.hrmApp.dto.EmployeeHistoryStatsDTO;
import com.paw.hrmApp.service.EmployeeHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = { SpringFoxConfig.employeeHistory })
public class EmployeeHistoryController {
    private final EmployeeHistoryService employeeHistoryService;

    @ApiOperation(value = "Returns list of all archived employees")
    @GetMapping("/employee-history")
    private List<EmployeeHistoryDTO> getEmployeeHistory() {
        return employeeHistoryService.getEmployeeHistory();
    }

    @ApiOperation(value = "Returns specific archived employee")
    @GetMapping("/employee-history/{id}")
    private EmployeeHistoryDTO getSpecificEmployeeHistory(@ApiParam(value = "Id of archived employee", example = "1", required = true) @PathVariable Long id) {
        return employeeHistoryService.getParticularEmployeeHistory(id);
    }

    @ApiOperation(value = "Returns statistics from specific date")
    @GetMapping("/employee-history/statistic/{date}")
    private EmployeeHistoryStatsDTO getStatistics(@ApiParam(value = "Date from which statistics should be made", example = "30-03-2021", required = true) @PathVariable String date) throws ParseException {
        return employeeHistoryService.getStatistics(date);
    }

    @ApiOperation(value = "Delete specific employee from history")
    @DeleteMapping("/employee-history/{id}")
    private void deleteEmployeeHistory(@ApiParam(value = "Id of archived employees", example = "1", required = true) @PathVariable Long id) {
        employeeHistoryService.deleteEmployeeHistory(id);
    }
}
