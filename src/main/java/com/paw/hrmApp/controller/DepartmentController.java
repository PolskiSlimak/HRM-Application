package com.paw.hrmApp.controller;

import com.paw.hrmApp.configuration.SpringFoxConfig;
import com.paw.hrmApp.dto.DepartmentDTO;
import com.paw.hrmApp.dto.DepartmentStatsDTO;
import com.paw.hrmApp.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = { SpringFoxConfig.department })
public class DepartmentController {
    private final DepartmentService departmentService;

    @ApiOperation(value = "Returns list of all departments")
    @GetMapping("/departments")
    private List<DepartmentDTO> getDepartment() {
        return departmentService.getDepartments();
    }

    @ApiOperation(value = "Returns specific department")
    @GetMapping("/departments/{id}")
    private DepartmentDTO getSpecificDepartment(@ApiParam(value = "Id of department", example = "1", required = true) @PathVariable Long id) {
        return departmentService.getParticularDepartment(id);
    }

    @ApiOperation(value = "Returns how many employees work in each department")
    @GetMapping("/departments/statistic")
    private DepartmentStatsDTO getStatistics() {
        return departmentService.getStatistics();
    }

    @ApiOperation(value = "Creates specific department")
    @PostMapping("/departments")
    private void createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.saveDepartment(departmentDTO);
    }

    @ApiOperation(value = "Delete specific department")
    @DeleteMapping("/departments/{id}")
    private void deleteDepartment(@ApiParam(value = "Id of department", example = "1", required = true) @PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
