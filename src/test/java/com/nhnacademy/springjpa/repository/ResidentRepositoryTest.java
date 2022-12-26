package com.nhnacademy.springjpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.domain.ResidentNameOnly;
import com.nhnacademy.springjpa.entity.Resident;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class ResidentRepositoryTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    ResidentRepository residentRepository;

    @Test
    @DisplayName("Repository List")
    void test() {
        List<Resident> residentList = residentRepository.findAll();

        assertThat(residentList.size()).isEqualTo(7L);
    }

    @Test
    @DisplayName("@Query 이용한 JPQL")
    void test2() {
        Resident resident = residentRepository.findByName("남길동");

        int result = residentRepository.updateResident_RegistrationBaseAddress(1L, "마포");
        residentRepository.flush();

        em.clear();

        assertThat(residentRepository.findById(1L).get().getRegistrationBaseAddress()).isEqualTo("마포");
    }

    @Test
    @DisplayName("Dto 반환하는 메서드")
    void test3() {
//        ResidentNameOnly residentNameOnly = residentRepository.findByResidentSerialNumber(1L);
//        assertThat(residentNameOnly.getName()).isEqualTo("남길동");
    }
}
