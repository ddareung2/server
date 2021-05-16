package com.ddareung2.server.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginResponse {

    @NotNull
    private String accessToken;
}
