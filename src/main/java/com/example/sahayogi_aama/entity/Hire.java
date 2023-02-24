package com.example.sahayogi_aama.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="hire")
public class Hire {
    @Id
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "payment")
    private String payment;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "hired_aama_FK", referencedColumnName = "id")
    private Aama aama_id;
    @ManyToOne
    @JoinColumn(name = "hire_user_FK", referencedColumnName = "id")
    private User user_id;

}
