package com.paw.hrmApp.controller;

import com.paw.hrmApp.configuration.SpringFoxConfig;
import com.paw.hrmApp.dto.JWTokenDTO;
import com.paw.hrmApp.dto.UserDTO;
import com.paw.hrmApp.model.UserDetailsImpl;
import com.paw.hrmApp.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = { SpringFoxConfig.user })
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/login")
    public JWTokenDTO login(@Valid @RequestBody UserDTO userDTO) {
        return userService.authenticateAndGetToken(userDTO);
    }

    @PostMapping(value = "/logout")
    public void logout() {}

    @PostMapping(value = "/signup")
    public void signup(@Valid @RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.deleteUser(userDetails);
    }

}
