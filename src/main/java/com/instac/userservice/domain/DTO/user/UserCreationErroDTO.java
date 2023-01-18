package com.instac.userservice.domain.DTO.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Getter @Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserCreationErroDTO {
    List<String> errors;
}
