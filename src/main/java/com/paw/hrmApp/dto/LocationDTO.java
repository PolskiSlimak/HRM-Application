package com.paw.hrmApp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocationDTO {
    private Long locationId;
    private String city;
    private String countryName;
}
