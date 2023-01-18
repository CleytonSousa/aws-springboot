package com.instac.userservice.services;

import com.google.gson.Gson;
import com.instac.userservice.domain.DTO.user.UserDTO;
import com.instac.userservice.domain.UserEntity;
import com.instac.userservice.exceptions.UserCreationException;
import com.instac.userservice.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private UserRepository userRepository;
    private S3Service s3Service;
    private ModelMapper modelMapper;
    private Gson gson;


    @Autowired
    public UserService(UserRepository userRepository, S3Service s3Services, ModelMapper modelMapper, Gson gson){
        this.userRepository = userRepository;
        this.s3Service = s3Services;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }
    public UserDTO createUser(String userS, MultipartFile file) throws IOException {
        UserEntity userEntity = null;
        UserDTO user = gson.fromJson(userS, UserDTO.class);

        String erros = verifyUserExist(user);
        if(erros == null){
            user.setProfilePicUrl(s3Service.saveImage(file.getOriginalFilename(), file.getInputStream()));
            userEntity = userRepository.save(modelMapper.map(user, UserEntity.class));
            return modelMapper.map(userEntity, UserDTO.class);
        } else {
            throw new UserCreationException(erros);
        }
    }

    private String verifyUserExist(UserDTO userDTO){
        List<String> erros = new ArrayList<>();
        if(!ObjectUtils.isEmpty(userRepository.findByUserEmail(userDTO.getUserEmail()))){
            erros.add("Esse email já se encontra cadastrado!: " + userDTO.getUserEmail());
        }
        if(!ObjectUtils.isEmpty(userRepository.findByUserPhone(userDTO.getUserPhone()))){
            erros.add(" Esse numero de telefone já se encontra cadastrado!: "+userDTO.getUserPhone());
        }
        if(!ObjectUtils.isEmpty(userRepository.findByUsername(userDTO.getUsername()))){
            erros.add(" Esse nome de usuario já foi usado por alguém, tente outro! :b");
        }

        if(!erros.isEmpty()){
            return String.join(",", erros);
        } else {
            return null;
        }
    }
}
