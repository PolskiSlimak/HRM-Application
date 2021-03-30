package com.paw.hrmApp.controller;

import com.paw.hrmApp.configuration.SpringFoxConfig;
import com.paw.hrmApp.dto.LocationDTO;
import com.paw.hrmApp.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = { SpringFoxConfig.location })
public class LocationsController {
    private final EmployeeService employeeService;

    @ApiOperation(value = "Returns all location")
    @GetMapping("/locations")
    private LocationDTO getLocations() {
        LocationDTO locationDTO = new LocationDTO();
        return locationDTO;
    }

    @ApiOperation(value = "Returns data from specific location")
    @GetMapping("/locations/{id}")
    private LocationDTO getSpecificLocations(@ApiParam(value = "Id of location", example = "1", required = true) @PathVariable Long id) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocationId(id);
        return locationDTO;
    }

    @ApiOperation(value = "Returns statistics related to locations")
    @GetMapping("/locations/statistics")
    private Map<String, Integer> getStatistics() {
        return new HashMap<>();
    }

    @ApiOperation(value = "Creates location")
    @PostMapping("/locations")
    private void createLocation() {
        employeeService.saveLocation();
    }

    @ApiOperation(value = "Deletes specific location")
    @DeleteMapping("/locations/{id}")
    private void deleteLocation(@ApiParam(value = "Id of location", example = "1", required = true) @PathVariable Long id) {

    }
}
