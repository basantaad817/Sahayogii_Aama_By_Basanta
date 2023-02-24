package com.example.sahayogi_aama.services;

import com.example.sahayogi_aama.entity.Hire;
import com.example.sahayogi_aama.pojo.HirePojo;

import java.util.*;

public interface HireService {
    HirePojo save(HirePojo hirePojo);
    List<Hire> findAll();

    List<Hire> findApplicationById(Integer id);

    void deleteByID(Integer id);

}
