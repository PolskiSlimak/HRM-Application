package com.paw.hrmApp.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LocationCreateDTO {
    private String countryName;
    private String cityName;
    private String postalCode;
    private String streetAddress;
}
