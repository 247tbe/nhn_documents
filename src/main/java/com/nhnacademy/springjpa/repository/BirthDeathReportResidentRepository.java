package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportResidentRepository
    extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.BDRRPk> {
    BirthDeathReportResident findByEmailAddress(String emailAddress);

}
