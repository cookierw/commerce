package com.seanrw.commerce.dtos.responses;

import lombok.Getter;

public class LoginSuccessResponse {
    
    @Getter
    private String username;
    @Getter
    private String token;
    
    public LoginSuccessResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
