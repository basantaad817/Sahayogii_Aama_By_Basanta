package com.example.sahayogi_aama.repo;

import com.example.sahayogi_aama.entity.Aama;
import com.example.sahayogi_aama.entity.Hire;
import com.example.sahayogi_aama.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AamaRepo  extends JpaRepository<Aama, Integer> {

    @Query(value = "SELECT * FROM aama where id=?1", nativeQuery = true)
    List<Aama> findAamaById(Integer id);

    Optional<Aama> findByEmail(String s);
}
