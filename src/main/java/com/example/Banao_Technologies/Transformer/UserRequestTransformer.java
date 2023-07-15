package com.example.Banao_Technologies.Transformer;

import com.example.Banao_Technologies.DTOs.UserRequestDto;
import com.example.Banao_Technologies.Entity.User;

public class UserRequestTransformer {

    public static User userRequestDtoToUser(UserRequestDto userRequestDto){

        return User.builder().name(userRequestDto.getName()).password(userRequestDto.getPassword()).
                email(userRequestDto.getEmail()).build();

    }
}
