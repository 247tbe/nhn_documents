package com.nhnacademy.springjpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import com.nhnacademy.springjpa.entity.FamilyRelationship;
import com.nhnacademy.springjpa.entity.Resident;
import java.util.Optional;
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
public class BirthDeathReportResidentRepositoryTest {
    @Autowired
    BirthDeathReportResidentRepository birthDeathReportResidentRepository;

    @Test
    void test() {
        BirthDeathReportResident birthDeathReportResident =
            birthDeathReportResidentRepository.findByEmailAddress("nam@nhnad.co.kr");

        assertThat(birthDeathReportResident.getResident().getName()).isEqualTo("남기석");
        assertThat(birthDeathReportResident.getReportResident().getName()).isEqualTo("남기준");
    }
}
