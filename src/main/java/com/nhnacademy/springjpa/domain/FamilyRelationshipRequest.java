package com.nhnacademy.springjpa.domain;

import lombok.Data;

@Data
public class FamilyRelationshipRequest {
    private Long baseResidentSerialNumber;
    private Long familyResidentSerialNumber;
    private String familyRelationshipCode;
}
