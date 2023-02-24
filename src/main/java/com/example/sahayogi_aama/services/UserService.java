package com.example.sahayogi_aama.services;

import com.example.sahayogi_aama.entity.Aama;
import com.example.sahayogi_aama.entity.User;
import com.example.sahayogi_aama.pojo.UserPojo;

import java.io.IOException;

public interface UserService {
    UserPojo save(UserPojo userPojo);
    User findByEmail(String email);



}
