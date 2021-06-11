package com.paw.hrmApp.mapper;

import com.paw.hrmApp.dto.LocationDTO;
import com.paw.hrmApp.dto.LocationStatsDTO;
import com.paw.hrmApp.model.LocationEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationMapper {
    public static LocationDTO mapToLocationDTO(LocationEntity locationEntity) {
        return LocationDTO.builder()
                .locationId(locationEntity.getLocationId())
                .countryName(locationEntity.getCountryName())
                .cityName(locationEntity.getCityName())
                .postalCode(locationEntity.getPostalCode())
                .streetAddress(locationEntity.getStreetAddress())
                .build();
    }

    public static void overrideLocationEntity(LocationEntity locationEntity, HashMap<String, String> details) {
        locationEntity.setCountryName(details.get("countryName"));
        locationEntity.setStreetAddress(details.get("streetAddress"));
        locationEntity.setPostalCode(details.get("postalCode"));
        locationEntity.setCityName(details.get("cityName"));
    }

    public static List<LocationStatsDTO> mapToLocationStatsDTOList(List<Map<String, Object>> stats) {
        List<LocationStatsDTO> statsDTOList = new ArrayList<>();
        for (Map<String, Object> map : stats) {
            String cityName = (String) map.get("cityName");
            Integer departmentCount = (Integer) map.get("departmentCount");
            LocationStatsDTO locationStatsDTO = new LocationStatsDTO(cityName, departmentCount);
            statsDTOList.add(locationStatsDTO);
        }
        return statsDTOList;
    }
}
