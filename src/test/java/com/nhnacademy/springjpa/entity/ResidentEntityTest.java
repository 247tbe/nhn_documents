package com.nhnacademy.springjpa.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
    @ContextConfiguration(classes = RootConfig.class),
    @ContextConfiguration(classes = WebConfig.class)
})
public class ResidentEntityTest {
    @Autowired
    ResidentRepository residentRepository;

    @Test
    @DisplayName("Using ResidentRepository Test")
    void test() {
        Resident resident = new Resident();

        resident.setResidentSerialNumber(8L);
        resident.setName("정세현");
        resident.setResidentRegistrationNumber("000000-1000000");
        resident.setGenderCode("남");
        resident.setBirthDate(LocalDateTime.now());
        resident.setBirthPlaceCode("병원");
        resident.setRegistrationBaseAddress("NHN");

        residentRepository.saveAndFlush(resident);

        assertThat(residentRepository.findById(resident.getResidentSerialNumber())
            .get().getResidentSerialNumber()).isEqualTo(resident.getResidentSerialNumber());
    }
}
