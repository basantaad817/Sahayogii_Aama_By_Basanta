package com.example.sahayogi_aama.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Collection;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="aama", uniqueConstraints = {
        @UniqueConstraint(name = "UNIQUE_user_email", columnNames = "email")
})

public class Aama  {
    @Id
    @SequenceGenerator(name = "aama_seq_gen", sequenceName = "aama_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "aama_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column()
    private String email;

    @Column(name = "location")
    private String location;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "knownfor")
    private String knownfor;


    @Column(name = "phoneNo")
    private String phoneNo;


    @Column(name = "image")
    private String image;

    @Transient
    private String imageBase64;


}