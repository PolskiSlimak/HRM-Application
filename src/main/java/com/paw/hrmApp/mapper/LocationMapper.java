package com.paw.hrmApp.mapper;

import com.paw.hrmApp.dto.LocationDTO;
import com.paw.hrmApp.dto.LocationStatsDTO;
import com.paw.hrmApp.model.LocationEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocationMapper {
    public static LocationDTO mapToLocationDTO(LocationEntity locationEntity) {
        return LocationDTO.builder()
                .countryName(locationEntity.getCountryName())
                .cityName(locationEntity.getCityName())
                .postalCode(locationEntity.getPostalCode())
                .streetAddress(locationEntity.getStreetAddress())
                .build();
    }

    public static LocationEntity mapToLocationEntity(LocationDTO locationDTO) {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCountryName(locationDTO.getCountryName());
        locationEntity.setStreetAddress(locationDTO.getStreetAddress());
        locationEntity.setPostalCode(locationDTO.getPostalCode());
        locationEntity.setStreetAddress(locationDTO.getStreetAddress());
        locationEntity.setCityName(locationDTO.getCityName());
        return locationEntity;
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
