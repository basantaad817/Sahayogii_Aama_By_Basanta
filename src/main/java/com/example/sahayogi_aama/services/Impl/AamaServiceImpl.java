package com.example.sahayogi_aama.services.Impl;


import com.example.sahayogi_aama.Exception.AppException;
import com.example.sahayogi_aama.config.PasswordEncoderUtil;
import com.example.sahayogi_aama.entity.Aama;
import com.example.sahayogi_aama.entity.Hire;
import com.example.sahayogi_aama.entity.User;
import com.example.sahayogi_aama.pojo.AamaPojo;
import com.example.sahayogi_aama.pojo.UserPojo;
import com.example.sahayogi_aama.repo.AamaRepo;
import com.example.sahayogi_aama.repo.UserRepo;
import com.example.sahayogi_aama.services.AamaService;
import com.example.sahayogi_aama.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AamaServiceImpl implements AamaService {
    private  final UserRepo userRepo;
    private final AamaRepo aamaRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/Aama_photo";


    @Override
    public AamaPojo save(AamaPojo aamaPojo) throws IOException {
        Aama aama;
        if (aamaPojo.getId() != null) {
            aama = aamaRepo.findById(aamaPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            aama = new Aama();
        }
        aama.setFirstname(aamaPojo.getFirstname());
        aama.setLastname((aamaPojo.getLastname()));
        aama.setEmail(aamaPojo.getEmail());
        aama.setLocation((aamaPojo.getLocation()));
        aama.setExperience((aamaPojo.getExperience()));
        aama.setKnownfor((aamaPojo.getKnownfor()));
        aama.setPhoneNo((aamaPojo.getPhoneNo()));

        if(aamaPojo.getImage()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, aamaPojo.getImage().getOriginalFilename());
            fileNames.append(aamaPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, aamaPojo.getImage().getBytes());
            aama.setImage(aamaPojo.getImage().getOriginalFilename());
        }


        aamaRepo.save(aama);
        return new AamaPojo(aama);


    }

    @Override
    public List<Aama> findAll() {
        return findAllInList(aamaRepo.findAll());
    }

    @Override
    public Aama fetchById(Integer id) {
        Aama aama=aamaRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        aama=Aama.builder()
                .id(aama.getId())
                .imageBase64(getImageBase64(aama.getImage()))
                .knownfor(aama.getKnownfor())
                .experience(aama.getExperience())
                .firstname(aama.getFirstname())
                .lastname(aama.getLastname())
                .phoneNo(aama.getPhoneNo())
                .email(aama.getEmail())
                .location(aama.getLocation())
                .build();
        return aama;

    }


//    @Override
//    public List<Aama> findAamaById(Integer id) {
//        return findAllInList(aamaRepo.findAamaById(id));
//    }


    @Override
    public void deleteById(Integer id) {
        aamaRepo.deleteById(id);
    }

    public  List<Aama> findAllInList(List<Aama> aamaList){
        Stream<Aama> allJobsWithImage = aamaList.stream().map(aama ->
                Aama.builder()
                        .id(aama.getId())
                        .location(aama.getLocation())
                        .phoneNo(aama.getPhoneNo())
                        .imageBase64(getImageBase64(aama.getImage()))
                        .email(aama.getEmail())
                        .firstname(aama.getFirstname())
                        .lastname(aama.getLastname())
                        .experience(aama.getExperience())
                        .knownfor(aama.getKnownfor())
                        .build()
        );
        aamaList = allJobsWithImage.toList();
        return aamaList;
    }


    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/Aama_photo/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }


}