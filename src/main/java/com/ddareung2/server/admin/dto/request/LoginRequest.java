package com.ddareung2.server.admin.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LoginRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
