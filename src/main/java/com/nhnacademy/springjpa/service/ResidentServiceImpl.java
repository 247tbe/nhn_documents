package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.domain.ResidentRegisterRequest;
import com.nhnacademy.springjpa.entity.Authority;
import com.nhnacademy.springjpa.entity.HouseholdCompositionResident;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
import com.nhnacademy.springjpa.repository.HouseholdCompositionResidentRepository;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("residentService")
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final PasswordEncoder passwordEncoder;

    public ResidentServiceImpl(ResidentRepository residentRepository,
                               HouseholdCompositionResidentRepository householdCompositionResidentRepository,
                               PasswordEncoder passwordEncoder) {
        this.residentRepository = residentRepository;
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public List<Resident> getAllResidentsById(String id) {
        Resident resident = residentRepository.findById(id);
        Long residentSerialNumber = resident.getResidentSerialNumber();
        HouseholdCompositionResident householdCompositionResident =
            householdCompositionResidentRepository.findByPkResidentSerialNumber(
                residentSerialNumber);

        Long householdSerialNumber =
            householdCompositionResident.getPk().getHouseholdSerialNumber();
        List<Long> residentSerialNumbers =
            householdCompositionResidentRepository.getResidentSerialNumbersByHouseholdSerialNumber(
                householdSerialNumber);
        Iterator<Long> residentSerialNumbersIterator = residentSerialNumbers.listIterator();
        List<Resident> residents = new ArrayList<>();

        while (residentSerialNumbersIterator.hasNext()) {
            residents.add(residentRepository.findByResidentSerialNumber(
                residentSerialNumbersIterator.next()));
        }

        return residents;
    }

    @Override
    public ResidentDto getResident(Long serialNumber) {
        Resident resident = residentRepository.findById(serialNumber)
            .orElseThrow(() -> new ResidentNotFoundException(serialNumber));

        ResidentDto residentDto = new ResidentDto();
        residentDto.setResidentSerialNumber(resident.getResidentSerialNumber());
        residentDto.setName(resident.getName());
        residentDto.setResidentRegistrationNumber(resident.getResidentRegistrationNumber());
        residentDto.setGenderCode(resident.getGenderCode());
        residentDto.setBirthDate(resident.getBirthDate());
        residentDto.setBirthPlaceCode(resident.getBirthPlaceCode());
        residentDto.setRegistrationBaseAddress(resident.getRegistrationBaseAddress());
        residentDto.setDeathDate(resident.getDeathDate());
        residentDto.setDeathPlaceCode(resident.getDeathPlaceCode());
        residentDto.setDeathPlaceAddress(resident.getDeathPlaceAddress());
        residentDto.setId(resident.getId());
        residentDto.setPassword(resident.getPassword());
        residentDto.setEmail(resident.getEmail());
        residentDto.setAuthority(resident.getAuthority().getAuthority());

        return residentDto;
    }

    @Transactional
    @Override
    public Resident createResident(ResidentRegisterRequest residentRegisterRequest) {
        Resident resident = new Resident();
        resident.setResidentSerialNumber(residentRegisterRequest.getResidentSerialNumber());
        resident.setName(residentRegisterRequest.getName());
        resident.setResidentRegistrationNumber(
            residentRegisterRequest.getResidentRegistrationNumber());
        resident.setGenderCode(residentRegisterRequest.getGenderCode());
        resident.setBirthDate(LocalDateTime.now());
        resident.setBirthPlaceCode(residentRegisterRequest.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(residentRegisterRequest.getRegistrationBaseAddress());
        resident.setDeathDate(residentRegisterRequest.getDeathDate());
        resident.setDeathPlaceCode(residentRegisterRequest.getDeathPlaceCode());
        resident.setDeathPlaceAddress(residentRegisterRequest.getDeathPlaceAddress());
        resident.setId(residentRegisterRequest.getId());
        resident.setPassword(passwordEncoder.encode(residentRegisterRequest.getPassword()));
        resident.setEmail(residentRegisterRequest.getEmail());

        Authority authority = new Authority();
        authority.setResident(resident);
        authority.setAuthority(residentRegisterRequest.getAuthority());

        resident.setAuthority(authority);

        return residentRepository.saveAndFlush(resident);
    }

    @Transactional
    @Override
    public Resident modifyResident(Long serialNumber,
                                   ResidentRegisterRequest residentRegisterRequest) {
        Resident resident = residentRepository.findByResidentSerialNumber(serialNumber);
        resident.setId(residentRegisterRequest.getId());
        resident.setPassword(passwordEncoder.encode(residentRegisterRequest.getPassword()));
        resident.setEmail(residentRegisterRequest.getEmail());

        Authority authority = new Authority();
        authority.setResident(resident);
        authority.setAuthority(residentRegisterRequest.getAuthority());

        resident.setAuthority(authority);

        return residentRepository.saveAndFlush(resident);
    }

    @Override
    public boolean matches(String id, String password) {
        Resident resident = residentRepository.findById(id);

        return resident.getPassword().equals(password);
    }
}
