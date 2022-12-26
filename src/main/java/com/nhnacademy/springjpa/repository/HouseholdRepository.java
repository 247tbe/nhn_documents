package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Long> {

}
