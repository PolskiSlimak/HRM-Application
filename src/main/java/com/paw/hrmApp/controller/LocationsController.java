package com.paw.hrmApp.controller;

import com.paw.hrmApp.configuration.SpringFoxConfig;
import com.paw.hrmApp.dto.LocationCreateDTO;
import com.paw.hrmApp.dto.LocationDTO;
import com.paw.hrmApp.dto.LocationStatsDTO;
import com.paw.hrmApp.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = { SpringFoxConfig.location })
public class LocationsController {
    private final LocationService locationService;

    @ApiOperation(value = "Returns all location")
    @GetMapping("/locations")
    private List<LocationDTO> getLocations() {
        return locationService.getLocations();
    }

    @ApiOperation(value = "Returns data from specific location")
    @GetMapping("/locations/{id}")
    private LocationDTO getSpecificLocations(@ApiParam(value = "Id of location", example = "1", required = true) @PathVariable Long id) {
        return locationService.getParticularLocation(id);
    }

    @ApiOperation(value = "Returns statistics related to locations")
    @GetMapping("/locations/statistics")
    private List<LocationStatsDTO> getStatistics() {
        return locationService.getStatistics();
    }

    @ApiOperation(value = "Edit location")
    @PutMapping("/locations")
    private void editLocation(@RequestBody LocationDTO locationDTO) {
        locationService.editLocation(locationDTO);
    }

    @ApiOperation(value = "Create location")
    @PostMapping("/locations")
    private void createLocation(@RequestBody LocationCreateDTO locationDTO) {
        locationService.createLocation(locationDTO);
    }

    @ApiOperation(value = "Deletes specific location")
    @DeleteMapping("/locations/{id}")
    private void deleteLocation(@ApiParam(value = "Id of location", example = "1", required = true) @PathVariable Long id) {
        locationService.deleteLocation(id);
    }
}
