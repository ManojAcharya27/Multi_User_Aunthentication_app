package com.example.Banao_Technologies.Service.ServiceInterface.ServiceImpl;

import com.example.Banao_Technologies.DTOs.EmailOtpRequestDto;
import com.example.Banao_Technologies.DTOs.UserRequestDto;
import com.example.Banao_Technologies.DTOs.UserResponseDto;
import com.example.Banao_Technologies.Entity.Admin;
import com.example.Banao_Technologies.Entity.User;
import com.example.Banao_Technologies.Exceptions.InvalidOtpException;
import com.example.Banao_Technologies.Exceptions.InvalidPassword;
import com.example.Banao_Technologies.Exceptions.InvalidUserException;
import com.example.Banao_Technologies.Exceptions.UserSignUpException;
import com.example.Banao_Technologies.Repository.AdminRepository;
import com.example.Banao_Technologies.Repository.UserRepository;
import com.example.Banao_Technologies.Service.ServiceInterface.UserInterface;
import com.example.Banao_Technologies.Transformer.UserRequestTransformer;
import com.example.Banao_Technologies.Util.EmailOtpUtil;
import com.example.Banao_Technologies.Util.EmailSendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailOtpUtil emailOtpUtil;

    @Autowired
    EmailSendUtil emailSendUtil;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) throws Exception {
        if(userRequestDto.getEmail()==null||userRequestDto.getPassword()==null){
            throw new UserSignUpException("Invalid Details");
        }

        if(!userRequestDto.getPassword().equals(userRequestDto.getConfirmPassword())){
            throw new InvalidPassword("Please Match Your Password");
        }

        User userAlreadyPresent=userRepository.findByEmail(userRequestDto.getEmail());

        if(userAlreadyPresent!=null){
            throw new InvalidUserException("User Already Present.!");
        }

       /*  if(user1!=null){
            throw new Exception("User Already present");
        }*/


         String otp=emailOtpUtil.otpGenerate();
         try {
             emailSendUtil.sendOtpEmail(userRequestDto.getEmail(),otp);
         }catch (Exception e){
             throw new Exception("Unable send otp please try again");
         }

        User user= UserRequestTransformer.userRequestDtoToUser(userRequestDto);
         user.setOtp(otp);
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setName(user.getName());
        User savedUser=userRepository.save(user);
        return userResponseDto;
    }

    @Override
    public UserResponseDto activatingUser(EmailOtpRequestDto emailOtpRequestDto) throws InvalidOtpException {

        User user=userRepository.findByOtp(emailOtpRequestDto.getOtp());
        if(user==null)throw new InvalidOtpException("Invalid Otp");
        user.setActive(true);
        User savedUser= userRepository.save(user);
        List<Admin> adminList=adminRepository.findAll();
        return UserResponseDto.builder().name(user.getName()).build();


    }

    @Override
    public UserResponseDto loginUser(UserRequestDto userRequestDto) throws InvalidUserException {

        User user=userRepository.findByEmail(userRequestDto.getEmail());
        if(user==null||user.isActive()!=true||!user.getPassword().equals(userRequestDto.getPassword())){
            throw new InvalidUserException("Please Register to Application");
        }
        return UserResponseDto.builder().name(user.getName()).build();
    }
}
