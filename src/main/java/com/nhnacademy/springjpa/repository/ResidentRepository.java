package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
    Resident findByName(String name);
    Resident findByResidentSerialNumber(Long serialNumber);
    Resident findById(String id);
    boolean findByEmail(String email);

    @Modifying
    @Query("update Resident r " +
        "set r.registrationBaseAddress = :registrationBaseAddress " +
        "where r.residentSerialNumber = :residentSerialNumber")
    int updateResident_RegistrationBaseAddress(@Param("residentSerialNumber") Long residentSerialNumber,
                       @Param("registrationBaseAddress") String registrationBaseAddress);

//    ResidentNameOnly findByResidentSerialNumber(Long serialNumber);

}
