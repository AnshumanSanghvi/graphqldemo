package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.data.enums.Role;
import com.anshuman.graphqldemo.model.entity.User;
import com.anshuman.graphqldemo.model.repository.UserRepository;
import com.anshuman.graphqldemo.resource.dto.JwtAuthenticationResponse;
import com.anshuman.graphqldemo.resource.dto.SignUpRequest;
import com.anshuman.graphqldemo.resource.dto.SigninRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse signIn(SigninRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        log.info("AuthService - Authentication: {}", authentication);

        var user = userRepository
                .findByUserNameIgnoreCase(request.getUserName())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        log.info("AuthService - user: {}", user);

        var jwt = jwtService.generateToken(user);
        log.info("AuthService - jwt: {}", jwt);

        var response = JwtAuthenticationResponse.builder().token(jwt).build();
        log.info("AuthService - jwtResponse: {}", response);

        return response;
    }
}
