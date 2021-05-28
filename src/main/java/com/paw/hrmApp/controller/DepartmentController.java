package com.paw.hrmApp.controller;

import com.paw.hrmApp.configuration.SpringFoxConfig;
import com.paw.hrmApp.dto.DepartmentDTO;
import com.paw.hrmApp.service.EmployeeService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = { SpringFoxConfig.department })
public class DepartmentController {
    private final EmployeeService employeeService;

    @ApiOperation(value = "Returns list of all departments")
    @GetMapping("/departments")
    private List<DepartmentDTO> getDepartment() {
        List<DepartmentDTO> departmentDTO = new ArrayList<>();
        departmentDTO.add(DepartmentDTO.builder().build());
        return departmentDTO;
    }

    @ApiOperation(value = "Returns specific department")
    @GetMapping("/departments/{id}")
    private DepartmentDTO getSpecificDepartment(@ApiParam(value = "Id of department", example = "1", required = true) @PathVariable Long id) {
        DepartmentDTO departmentDTO = DepartmentDTO.builder().build();
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
//        employeeService.saveDepartment();
    }

    @ApiOperation(value = "Delete specific department")
    @DeleteMapping("/departments/{id}")
    private void deleteDepartment(@ApiParam(value = "Id of department", example = "1", required = true) @PathVariable Long id) {

    }
}
