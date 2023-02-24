package com.example.sahayogi_aama.pojo;

import com.example.sahayogi_aama.entity.Aama;
import com.example.sahayogi_aama.entity.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AamaPojo {
    private Integer id;

    private String firstname;
    private String lastname;
    private String email;
    private String location;
    private Integer experience;
    private String knownfor;
    private String phoneNo;
    private MultipartFile image;


    public AamaPojo(Aama aama) {
        this.id=aama.getId();
        this.firstname=aama.getFirstname();
        this.lastname=aama.getLastname();
        this.location=aama.getLocation();
        this.experience= aama.getExperience();
        this.knownfor=aama.getKnownfor();
        this.phoneNo=aama.getPhoneNo();
        this.email=aama.getEmail();
    }
}