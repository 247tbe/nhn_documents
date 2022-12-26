package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.HouseholdCompositionResident;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HCRPk> {
    @Query("select hcr " +
        "from HouseholdCompositionResident hcr " +
        "where hcr.pk.residentSerialNumber = ?1")
    HouseholdCompositionResident findByPkResidentSerialNumber(Long residentSerialNumber);

    @Query("select hcr.pk.residentSerialNumber " +
        "from HouseholdCompositionResident hcr " +
        "where hcr.pk.householdSerialNumber = ?1")
    List<Long> getResidentSerialNumbersByHouseholdSerialNumber(Long householdSerialNumber);

}
