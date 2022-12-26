package com.nhnacademy.springjpa.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ResidentRegisterRequest {
    private Long residentSerialNumber;
    private String name;
    private String residentRegistrationNumber;
    private String genderCode;
    private LocalDateTime birthDate;
    private String birthPlaceCode;
    private String registrationBaseAddress;
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;
    private String id;
    private String password;
    private String email;
    private String authority;
}
