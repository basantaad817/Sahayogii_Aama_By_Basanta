package com.example.sahayogi_aama.pojo;

import com.example.sahayogi_aama.entity.Hire;
import com.example.sahayogi_aama.entity.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HirePojo {
    private Integer id;

    private String payment;
    private  String description;
    private  String location;
    private  int user_id;
    private  int aama_id;

    public HirePojo(Hire hire) {
        this.id=hire.getId();
        this.payment=hire.getPayment();
        this.description=hire.getDescription();
        this.location=hire.getLocation();
        this.user_id=hire.getUser_id().getId();
        this.aama_id=hire.getAama_id().getId();
    }
}