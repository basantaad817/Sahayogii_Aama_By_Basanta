package com.example.sahayogi_aama;
import com.example.sahayogi_aama.entity.Hire;
import com.example.sahayogi_aama.repo.HireRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HireRepositoryTest {
    @Autowired
    private HireRepo hireRepo;


    @Test
    @Order(1)
    public void saveHireTest() {


        Hire hire = Hire.builder()
                .payment("3423")
                .location("Setopul")
                .description("Cleaning")
                .build();


        hireRepo.save(hire);

        Assertions.assertThat(hire.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getHireTest() {

        Hire hire = Hire.builder()
                .payment("3423")
                .location("Setopul")
                .description("Cleaning")
                .build();


        hireRepo.save(hire);


        Hire hireCreated = hireRepo.findById(hire.getId()).get();
        Assertions.assertThat(hireCreated.getId()).isEqualTo(hire.getId());

    }

    @Test
    @Order(3)
    public void getListOfHireTest() {
        Hire hire = Hire.builder()
                .payment("3423")
                .location("Setopul")
                .description("Cleaning")
                .build();


        hireRepo.save(hire);
        List<Hire> Hire = hireRepo.findAll();
        Assertions.assertThat(Hire.size()).isGreaterThan(0);
    }


    @Test
    @Order(4)
    public void updateHireTest() {

        Hire hire = Hire.builder()
                .payment("3423")
                .location("Setopul")
                .description("Cleaning")
                .build();


        hireRepo.save(hire);

        Hire hire1 = hireRepo.findById(hire.getId()).get();

        hire1.setLocation("Setopul");

        Hire hireUpdated = hireRepo.save(hire);

        Assertions.assertThat(hireUpdated.getLocation()).isEqualTo("Setopul");

    }

}