package com.paw.hrmApp.service;

import com.paw.hrmApp.dto.JWTokenDTO;
import com.paw.hrmApp.dto.UserDTO;
import com.paw.hrmApp.model.RoleEntity;
import com.paw.hrmApp.model.UserDetailsImpl;
import com.paw.hrmApp.model.UserEntity;
import com.paw.hrmApp.repository.RoleRepository;
import com.paw.hrmApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    public JWTokenDTO authenticateAndGetToken(UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDTO.getUsername(), userDTO.getPassword()
        ));

        return new JWTokenDTO(jwtUtils.generateToken(authentication));
    }

    @Transactional
    public void addUser(UserDTO userDTO) {
        if (userRepository.existsByUserName(userDTO.getUsername()))
            throw new RuntimeException("User already exist");
        Set<RoleEntity> roleSet = new HashSet<>();
        roleSet.add(roleRepository.findByRoleName("USER").orElseThrow());
        UserEntity user = new UserEntity();
        user.setUserName(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    public void deleteUser(UserDetailsImpl userDetails) {
        UserEntity user = getUserByUsername(userDetails.getUsername());
        userRepository.delete(user);
    }


    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Couldn't find user"));
    }
}
