package com.paw.hrmApp.service;

import com.paw.hrmApp.dao.FinderDAO;
import com.paw.hrmApp.dto.LocationCreateDTO;
import com.paw.hrmApp.dto.LocationDTO;
import com.paw.hrmApp.dto.LocationStatsDTO;
import com.paw.hrmApp.mapper.LocationMapper;
import com.paw.hrmApp.model.LocationEntity;
import com.paw.hrmApp.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final FinderDAO finderDAO;

    public List<LocationDTO> getLocations() {
        List<LocationEntity> locationEntityList = locationRepository.findAll();
        return mapToDTOList(locationEntityList);
    }

    public LocationDTO getParticularLocation(Long id) {
        LocationEntity locationEntity = finderDAO.getLocationEntity(id);
        return LocationMapper.mapToLocationDTO(locationEntity);
    }

    public void editLocation(LocationDTO locationDTO) {
        Long id = locationDTO.getLocationId();
        LocationEntity locationEntity = finderDAO.getLocationEntity(id);
        HashMap<String, String> details = new HashMap<>();
        details.put("countryName", locationDTO.getCountryName());
        details.put("streetAddress", locationDTO.getStreetAddress());
        details.put("postalCode", locationDTO.getPostalCode());
        details.put("cityName", locationDTO.getCityName());
        LocationMapper.overrideLocationEntity(locationEntity, details);
        locationRepository.save(locationEntity);
    }

    public void createLocation(LocationCreateDTO locationCreateDTO) {
        LocationEntity locationEntity = new LocationEntity();
        HashMap<String, String> detailsForCreation = new HashMap<>();
        detailsForCreation.put("countryName", locationCreateDTO.getCountryName());
        detailsForCreation.put("streetAddress", locationCreateDTO.getStreetAddress());
        detailsForCreation.put("postalCode", locationCreateDTO.getPostalCode());
        detailsForCreation.put("cityName", locationCreateDTO.getCityName());
        LocationMapper.overrideLocationEntity(locationEntity, detailsForCreation);
        locationRepository.save(locationEntity);
    }

    public List<LocationStatsDTO> getStatistics() {
        List<Map<String, Object>> stats = locationRepository.findLocationStats();
        return LocationMapper.mapToLocationStatsDTOList(stats);
    }

    public void deleteLocation(Long id) {
        LocationEntity locationEntity = finderDAO.getLocationEntity(id);
        locationRepository.delete(locationEntity);
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
