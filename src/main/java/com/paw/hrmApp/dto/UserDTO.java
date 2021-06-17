package com.paw.hrmApp.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UserDTO {
    private String username;
    private String password;
}