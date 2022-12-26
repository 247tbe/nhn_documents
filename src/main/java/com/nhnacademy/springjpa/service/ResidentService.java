package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.domain.ResidentRegisterRequest;
import com.nhnacademy.springjpa.entity.Resident;
import java.util.List;

public interface ResidentService {
    List<Resident> getAllResidentsById(String id);

    ResidentDto getResident(Long serialNumber);


    Resident createResident(ResidentRegisterRequest residentRegisterRequest);

    Resident modifyResident(Long serialNumber, ResidentRegisterRequest residentRegisterRequest);

    boolean matches(String id, String password);
}
