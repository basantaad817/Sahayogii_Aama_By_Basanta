package com.example.sahayogi_aama;

import com.example.sahayogi_aama.entity.Aama;
import com.example.sahayogi_aama.repo.AamaRepo;
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
public class AamaRepositoryTest {
    @Autowired
    private AamaRepo aamaRepo;


    @Test
    @Order(1)
    public void saveAamaTest() {


        Aama aama = Aama.builder()
                .firstname("Rupa")
                .lastname("shrestha")
                .location("Setopul")
                .experience(4)
                .knownfor("Cleaning")
                .email("rupashrest@gmail.com")
                .phoneNo("9818784565")
                .build();


        aamaRepo.save(aama);

        Assertions.assertThat(aama.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getAamaTest() {

        Aama aama = Aama.builder()
                .firstname("Rupa")
                .lastname("shrestha")
                .location("Setopul")
                .experience(4)
                .knownfor("Cleaning")
                .email("rupashrest@gmail.com")
                .phoneNo("9818784565")
                .build();


        aamaRepo.save(aama);


        Aama aamaCreated = aamaRepo.findById(aama.getId()).get();
        Assertions.assertThat(aamaCreated.getId()).isEqualTo(aama.getId());

    }

    @Test
    @Order(3)
    public void getListOfAamaTest() {
        Aama aama = Aama.builder()
                .firstname("Rupa")
                .lastname("shrestha")
                .location("Setopul")
                .experience(4)
                .knownfor("Cleaning")
                .email("rupashrest@gmail.com")
                .phoneNo("9818784565")
                .build();


        aamaRepo.save(aama);
        List<Aama> Aama = aamaRepo.findAll();
        Assertions.assertThat(Aama.size()).isGreaterThan(0);
    }


    @Test
    @Order(4)
    public void updateAamaTest() {

        Aama aama = Aama.builder()
                .firstname("Rupa")
                .lastname("shrestha")
                .location("Setopul")
                .experience(4)
                .knownfor("Cleaning")
                .email("rupashrest@gmail.com")
                .phoneNo("9818784565")
                .build();


        aamaRepo.save(aama);

        Aama aama1 = aamaRepo.findById(aama.getId()).get();

        aama1.setFirstname("Nita Yadav");

        Aama aamaUpdated = aamaRepo.save(aama);

        Assertions.assertThat(aamaUpdated.getFirstname()).isEqualTo("Gita Yadav");

    }

    @Test
    @Order(5)
    public void deleteAamaTest() {

        Aama aama = Aama.builder()
                .firstname("Rupa")
                .lastname("shrestha")
                .location("Setopul")
                .experience(4)
                .knownfor("Cleaning")
                .email("rupashrest@gmail.com")
                .phoneNo("9818784565")
                .build();


        aamaRepo.save(aama);

//        @Query(value = "SELECT * from")

        Aama aama1 = aamaRepo.findById(aama.getId()).get();
        aamaRepo.delete(aama1);

        Aama aama2 = null;
        Optional<Aama> optionalAama = aamaRepo.findByEmail("rupa@gmail.com");
        if (optionalAama.isPresent()) {
            aama2 = optionalAama.get();
        }

        Assertions.assertThat(aama2).isNull();
//        Assertions.assertThat(Aama1.getId()).isNull();
    }
}