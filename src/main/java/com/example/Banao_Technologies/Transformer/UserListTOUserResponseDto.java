package com.example.Banao_Technologies.Transformer;

import com.example.Banao_Technologies.DTOs.UserResponseDto;
import com.example.Banao_Technologies.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserListTOUserResponseDto {


    public  static List<UserResponseDto> userTOUserResponseDto(List<User> userList){
        List<UserResponseDto> userResponseDtoList=new ArrayList<>();
        for(int i=0;i<userList.size();i++){
            User user=userList.get(i);
            UserResponseDto userResponseDto=new UserResponseDto();
            if(user.isActive()){
                userResponseDto.setUserType("Active");
            }else{
                userResponseDto.setUserType("Not Active");
            }
            UserResponseDto.builder().name(user.getName()).email(user.getEmail()).build();
            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }
}
