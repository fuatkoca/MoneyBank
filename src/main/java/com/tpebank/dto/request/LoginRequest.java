package com.tpebank.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class LoginRequest {

   @NotNull(message="Please provide a username")
    private String userName;

    @NotNull(message="Please provide a password")
    private String password;

}