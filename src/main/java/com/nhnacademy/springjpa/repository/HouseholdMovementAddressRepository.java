package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.HMAPk> {
}
