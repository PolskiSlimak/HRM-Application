package com.paw.hrmApp.mapper;

import com.paw.hrmApp.dto.LocationDTO;
import com.paw.hrmApp.model.LocationEntity;

public class LocationMapper {
    public static LocationDTO mapToLocationDTO(LocationEntity locationEntity) {
        return LocationDTO.builder()
                .locationId(locationEntity.getLocationId())
                .city(locationEntity.getCityName())
                .countryName(locationEntity.getCountryName())
                .build();
    }
}
