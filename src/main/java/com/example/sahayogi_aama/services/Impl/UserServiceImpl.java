package com.example.sahayogi_aama.services.Impl;


import com.example.sahayogi_aama.Exception.AppException;
import com.example.sahayogi_aama.config.PasswordEncoderUtil;
import com.example.sahayogi_aama.entity.User;
import com.example.sahayogi_aama.pojo.AamaPojo;
import com.example.sahayogi_aama.pojo.UserPojo;
import com.example.sahayogi_aama.repo.UserRepo;
import com.example.sahayogi_aama.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserRepo userRepo;


    @Override
    public UserPojo save(UserPojo userPojo) {
        User user;
        if (userPojo.getId() != null) {
            user = userRepo.findById(userPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            user = new User();
        }
        user.setEmail(userPojo.getEmail());
        user.setFullname(userPojo.getFullname());
        user.setPhoneNo(userPojo.getMobile_no());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));
//
//        if(userPojo.getImage()!=null){
//            StringBuilder fileNames = new StringBuilder();
//            System.out.println(UPLOAD_DIRECTORY);
//            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, userPojo.getImage().getOriginalFilename());
//            fileNames.append(userPojo.getImage().getOriginalFilename());
//            Files.write(fileNameAndPath, userPojo.getImage().getBytes());
//            user.setImage(userPojo.getImage().getOriginalFilename());
//        }


        userRepo.save(user);
        return new UserPojo(user);


    }

    @Override
    public User findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        return user;
    }
}