package com.instac.userservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_usuario")
public class UserEntity {
    @Id
    @Column(name = "user_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_firstname")
    private String firstName;

    @Column(name = "user_lastname")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "user_bio")
    private String userBio;

    @Column(name = "user_profile_image")
    private String profilePicUrl;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_phone")
    private Integer userPhone;

}
