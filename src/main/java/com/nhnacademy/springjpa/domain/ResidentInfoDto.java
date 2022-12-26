package com.nhnacademy.springjpa.domain;

import lombok.Data;

@Data
public class ResidentInfoDto {
    private final String name;
    private final String password;
    private final String email;
}
