package com.paw.hrmApp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags="Location")
public class LocationsController {
    @ApiOperation(value = "Returns all location")
    @GetMapping("/locations")
    private void getLocations() {

    }

    @ApiOperation(value = "Returns data from specific location")
    @GetMapping("/locations/{id}")
    private void getSpecificLocations(@ApiParam(value = "Id of location", example = "1") @PathVariable int id) {

    }

    @ApiOperation(value = "Returns statistics related to locations")
    @GetMapping("/locations/statistics")
    private void getStatistics() {

    }

    @ApiOperation(value = "Creates location")
    @PostMapping("/locations")
    private void createLocation() {

    }

    @ApiOperation(value = "Deletes specific location")
    @DeleteMapping("/locations/{id}")
    private void createLocation(@ApiParam(value = "Id of location", example = "1") @PathVariable int id) {

    }
}
