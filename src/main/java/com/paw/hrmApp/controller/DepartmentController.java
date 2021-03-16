package com.paw.hrmApp.controller;

import com.paw.hrmApp.configuration.SpringFoxConfig;
import com.paw.hrmApp.dto.DepartmentDTO;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = { SpringFoxConfig.department })
public class DepartmentController {
    @ApiOperation(value = "Returns list of all departments")
    @GetMapping("/departments")
    private DepartmentDTO getDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        return departmentDTO;
    }

    @ApiOperation(value = "Returns specific department")
    @GetMapping("/departments/{id}")
    private DepartmentDTO getSpecificDepartment(@ApiParam(value = "Id of department", example = "1") @PathVariable Long id) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(id);
        return departmentDTO;
    }

    @ApiOperation(value = "Returns how many employees work in each department")
    @GetMapping("/departments/statistic")
    private Map<String, Integer> getStatistics() {
        return new HashMap<>();
    }

    @ApiOperation(value = "Creates specific department")
    @PostMapping("/departments")
    private void createDepartment() {

    }

    @ApiOperation(value = "Delete specific department")
    @DeleteMapping("/departments/{id}")
    private void deleteDepartment(@ApiParam(value = "Id of department", example = "1") @PathVariable Long id) {

    }
}
