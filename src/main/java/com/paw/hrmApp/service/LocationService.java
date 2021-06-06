package com.paw.hrmApp.service;

import com.paw.hrmApp.dto.LocationDTO;
import com.paw.hrmApp.dto.LocationStatsDTO;
import com.paw.hrmApp.mapper.LocationMapper;
import com.paw.hrmApp.model.LocationEntity;
import com.paw.hrmApp.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public List<LocationDTO> getLocations() {
        List<LocationEntity> locationEntityList = locationRepository.findAll();
        return mapToDTOList(locationEntityList);
    }

    public LocationDTO getParticularLocation(Long id) {
        LocationEntity locationEntity = locationRepository.findById(id).get();
        return LocationMapper.mapToLocationDTO(locationEntity);
    }

    public void saveLocation(LocationDTO locationDTO) {
        LocationEntity locationEntity = LocationMapper.mapToLocationEntity(locationDTO);
        locationRepository.save(locationEntity);
    }

    public List<LocationStatsDTO> getStatistics() {
        List<Map<String, Object>> stats = locationRepository.findLocationStats();
        return LocationMapper.mapToLocationStatsDTOList(stats);
    }

    public void deleteLocation(Long id) {
        locationRepository.findById(id);
    }

    private List<LocationDTO> mapToDTOList(List<LocationEntity> locationEntityList) {
        List<LocationDTO> locationDTOList = new ArrayList<>();
        for (LocationEntity locationEntity : locationEntityList) {
            LocationDTO locationDTO = LocationMapper.mapToLocationDTO(locationEntity);
            locationDTOList.add(locationDTO);
        }
        return locationDTOList;
    }
}
