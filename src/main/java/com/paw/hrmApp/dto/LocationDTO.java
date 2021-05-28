package com.paw.hrmApp.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class LocationDTO {
    private Long locationId;
    private String city;
    private String countryName;
}
