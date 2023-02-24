package com.example.sahayogi_aama.services.Impl;

import com.example.sahayogi_aama.config.PasswordEncoderUtil;
import com.example.sahayogi_aama.entity.Aama;
import com.example.sahayogi_aama.entity.Hire;
import com.example.sahayogi_aama.entity.User;
import com.example.sahayogi_aama.pojo.HirePojo;
import com.example.sahayogi_aama.pojo.UserPojo;
import com.example.sahayogi_aama.repo.AamaRepo;
import com.example.sahayogi_aama.repo.HireRepo;
import com.example.sahayogi_aama.repo.UserRepo;
import com.example.sahayogi_aama.services.HireService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HireServiceImpl implements HireService {
    private  final HireRepo hireRepo;
    private  final UserRepo userRepo;
    private  final AamaRepo aamaRepo;
    @Override
    public HirePojo save(HirePojo hirePojo) {
        Hire hire;
        if (hirePojo.getId() != null) {
            hire = hireRepo.findById(hirePojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            hire = new Hire();
        }
        if(hirePojo.getId()!=null){
            hire.setId(hire.getId());
        }
        hire.setAama_id(aamaRepo.findById(hirePojo.getAama_id()).orElseThrow());
        hire.setUser_id(userRepo.findById(hirePojo.getUser_id()).orElseThrow());
        hire.setDescription(hirePojo.getDescription());
        hire.setPayment(hirePojo.getPayment());
        hire.setLocation(hirePojo.getLocation());

        hireRepo.save(hire);
        return new HirePojo(hire);

    }

    @Override
    public List<Hire> findAll() {
        return findAllInList(hireRepo.findAll());
    }

    @Override
    public List<Hire> findApplicationById(Integer id) {
        return findAllInList(hireRepo.findApplicationById(id));

    }

    @Override
    public void deleteByID(Integer id) {
        hireRepo.deleteById(id);
    }

    public  List<Hire> findAllInList(List<Hire> hireList){
        Stream<Hire> allJobsWithImage = hireList.stream().map(hire ->
                Hire.builder()
                        .id(hire.getId())
                        .location(hire.getLocation())
                        .payment(hire.getPayment())
                        .user_id(hire.getUser_id())
                        .aama_id(hire.getAama_id())
                        .description(hire.getDescription())
                        .build()
        );
        hireList = allJobsWithImage.toList();
        return hireList;
    }

}
