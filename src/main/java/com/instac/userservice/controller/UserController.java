package com.instac.userservice.controller;

import com.instac.userservice.domain.DTO.user.UserDTO;
import com.instac.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestParam(value="data") String userDTO, @RequestParam(value="image") MultipartFile image) throws IOException {
        userService.createUser(userDTO, image);
        return ResponseEntity.ok("Usuario criado");
    }
}
