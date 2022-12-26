package com.nhnacademy.springjpa.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OAuthTokenDto {
    private String access_token;
    private String refresh_token;
    private Long expires_in;
    private String scope;
    private String token_type;
}
