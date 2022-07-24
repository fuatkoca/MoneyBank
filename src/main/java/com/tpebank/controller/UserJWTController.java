package com.tpebank.controller;


import com.tpebank.dto.request.LoginRequest;
import com.tpebank.dto.request.RegisterRequest;
import com.tpebank.dto.response.LoginResponse;
import com.tpebank.dto.response.ResponseMessages;
import com.tpebank.dto.response.TpeResponse;
import com.tpebank.security.JwtUtils;
import com.tpebank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping
public class UserJWTController {

    private UserService userService;

    private AuthenticationManager authManager;

    private JwtUtils jwtUtils;


    @PostMapping("/register")
    public ResponseEntity<TpeResponse> register(@Valid @RequestBody RegisterRequest registerRequest){

        userService.registerUser(registerRequest);

        TpeResponse response = new TpeResponse(true, ResponseMessages.REGISTER_RESPONSE_MESSAGE);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){

        Authentication authentication= authManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginRequest.getUserName(),loginRequest.getPassword()));

        String token= jwtUtils.generateJwtToken(authentication);

        LoginResponse response=new LoginResponse(token);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }






}
