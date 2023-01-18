package com.instac.userservice.domain.DTO.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String userBio;
    private String profilePicUrl;
    private String userEmail;
    private Integer userPhone;
}
