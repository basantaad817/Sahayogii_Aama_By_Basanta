package com.example.sahayogi_aama.pojo;

import com.example.sahayogi_aama.entity.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {
    private Integer id;

    private String email;
    private  String mobile_no;
    private  String fullname;
    private  String password;

//    private MultipartFile image;

    public UserPojo(User user) {
        this.id=user.getId();
        this.email=user.getEmail();
        this.mobile_no=user.getPhoneNo();
        this.fullname=user.getFullname();
        this.password=user.getPassword();
    }
}