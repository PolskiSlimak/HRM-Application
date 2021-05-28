package com.paw.hrmApp.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ManagerDTO {
    private Long managerId;
    private String firstName;
    private String lastName;
}
