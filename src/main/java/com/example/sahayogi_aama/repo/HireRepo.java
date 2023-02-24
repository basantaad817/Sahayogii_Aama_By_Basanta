package com.example.sahayogi_aama.repo;

import com.example.sahayogi_aama.entity.Hire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HireRepo extends JpaRepository<Hire,Integer> {
    @Query(value = "SELECT * FROM hire where hire_user_FK=?1", nativeQuery = true)
    List<Hire> findApplicationById(Integer id);
}
