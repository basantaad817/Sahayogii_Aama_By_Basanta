package com.example.sahayogi_aama.services;

import com.example.sahayogi_aama.entity.Aama;
import com.example.sahayogi_aama.pojo.AamaPojo;

import java.io.IOException;
import java.util.List;

public interface AamaService {
    AamaPojo save(AamaPojo aamaPojo) throws IOException;
     List<Aama> findAll();
    Aama fetchById(Integer id);
    void deleteById(Integer id);

//    List<Aama> findAamaById(Integer id);


}
