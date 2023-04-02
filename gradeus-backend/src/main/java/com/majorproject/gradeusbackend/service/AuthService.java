package com.majorproject.gradeusbackend.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.majorproject.gradeusbackend.entity.User;
import com.majorproject.gradeusbackend.model.AuthResponse;
import com.majorproject.gradeusbackend.model.LoginRequest;
import com.majorproject.gradeusbackend.model.RegisterRequest;
import com.majorproject.gradeusbackend.respository.UserRepository;
import com.majorproject.gradeusbackend.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .username(request.getUsername())
                .phoneNumber(request.getPhoneNumber())
                .build();
        try {
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception exception) {
            return AuthResponse.builder().message("Duplicate User Found!").build();
        }
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        User userObject = (User)user;
        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .username(userObject.getUsername())
                .email(userObject.getEmail())
                .firstname(userObject.getFirstname())
                .lastname(userObject.getLastname())
                .role(userObject.getRole().name())
                .build();
    }
}
