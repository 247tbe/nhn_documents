package com.nhnacademy.springjpa.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class EntityManagerTest {
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Create Resident")
    void test() {
        Resident resident = new Resident();

        resident.setResidentSerialNumber(8L);
        resident.setName("정세현");
        resident.setResidentRegistrationNumber("000000-1000000");
        resident.setGenderCode("남");
        resident.setBirthDate(LocalDateTime.now());
        resident.setBirthPlaceCode("병원");
        resident.setRegistrationBaseAddress("NHN");

        em.persist(resident);
        em.flush();

        Resident resident1 = em.find(Resident.class, 8L);
        assertThat(resident1).isEqualTo(resident);
    }

    @Test
    @DisplayName("Update Resident's Name")
    void test2() {
        Resident resident = new Resident();

        resident.setResidentSerialNumber(8L);
        resident.setName("정세현");
        resident.setResidentRegistrationNumber("000000-1000000");
        resident.setGenderCode("남");
        resident.setBirthDate(LocalDateTime.now());
        resident.setBirthPlaceCode("병원");
        resident.setRegistrationBaseAddress("NHN");

        em.persist(resident);

        resident.setName("현세정");
        em.flush();

        assertThat(resident.getName()).isEqualTo("현세정");
    }
}
