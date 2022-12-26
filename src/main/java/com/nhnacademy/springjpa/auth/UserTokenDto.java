package com.nhnacademy.springjpa.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserTokenDto {
    private String accessToken;
}
