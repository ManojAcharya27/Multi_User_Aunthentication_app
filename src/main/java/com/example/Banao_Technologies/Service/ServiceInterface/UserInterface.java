package com.example.Banao_Technologies.Service.ServiceInterface;

import com.example.Banao_Technologies.DTOs.EmailOtpRequestDto;
import com.example.Banao_Technologies.DTOs.UserRequestDto;
import com.example.Banao_Technologies.DTOs.UserResponseDto;
import com.example.Banao_Technologies.Exceptions.InvalidOtpException;
import com.example.Banao_Technologies.Exceptions.InvalidUserException;
import com.example.Banao_Technologies.Exceptions.UserSignUpException;

public interface UserInterface {

    public UserResponseDto registerUser(UserRequestDto userRequestDto) throws Exception;


    public UserResponseDto activatingUser(EmailOtpRequestDto emailOtpRequestDto) throws InvalidOtpException;


    public UserResponseDto loginUser(UserRequestDto userRequestDto) throws InvalidUserException;
}
