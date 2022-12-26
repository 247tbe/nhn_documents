package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.FamilyRelationshipRequest;
import com.nhnacademy.springjpa.entity.FamilyRelationship;

public interface FamilyRelationshipService {
    FamilyRelationship createRelationship(FamilyRelationshipRequest relationshipRequest);
    FamilyRelationship modifyRelationship(FamilyRelationshipRequest relationshipRequest);
    void deleteRelationship(FamilyRelationship familyRelationship);
}
