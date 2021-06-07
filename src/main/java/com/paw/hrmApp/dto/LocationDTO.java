package com.paw.hrmApp.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class LocationDTO {
    private Long locationId;
    private String countryName;
    private String cityName;
    private String postalCode;
    private String streetAddress;
}
