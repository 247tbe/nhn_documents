package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.FamilyRelationshipRequest;
import com.nhnacademy.springjpa.entity.FamilyRelationship;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.repository.FamilyRelationshipRepository;
import org.springframework.transaction.annotation.Transactional;

public class FamilyRelationshipImpl implements FamilyRelationshipService {
   private final FamilyRelationshipRepository relationshipRepository;

    public FamilyRelationshipImpl(FamilyRelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }

    @Transactional
    @Override
    public FamilyRelationship createRelationship(FamilyRelationshipRequest relationshipRequest) {
        FamilyRelationship familyRelationship = new FamilyRelationship();
        familyRelationship.setFamilyRelationshipCode(relationshipRequest.getFamilyRelationshipCode());

        return null;
    }

    @Transactional
    @Override
    public FamilyRelationship modifyRelationship(FamilyRelationshipRequest relationshipRequest) {
        return null;
    }

    @Override
    public void deleteRelationship(FamilyRelationship familyRelationship) {
        relationshipRepository.delete(familyRelationship);
    }
}
