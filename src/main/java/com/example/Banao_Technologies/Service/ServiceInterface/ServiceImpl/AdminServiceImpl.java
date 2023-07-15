package com.example.Banao_Technologies.Service.ServiceInterface.ServiceImpl;

import com.example.Banao_Technologies.DTOs.*;
import com.example.Banao_Technologies.Entity.Admin;
import com.example.Banao_Technologies.Entity.User;
import com.example.Banao_Technologies.Exceptions.AdminRegistrationException;
import com.example.Banao_Technologies.Repository.AdminRepository;
import com.example.Banao_Technologies.Repository.UserRepository;
import com.example.Banao_Technologies.Service.ServiceInterface.AdminServiceInterface;
import com.example.Banao_Technologies.Transformer.AdminRequestTOAdminTransformer;
import com.example.Banao_Technologies.Transformer.UserListTOUserResponseDto;
import com.example.Banao_Technologies.Util.EmailOtpUtil;
import com.example.Banao_Technologies.Util.EmailSendUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminServiceInterface {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailOtpUtil emailOtpUtil;

    @Autowired
    EmailSendUtil emailSendUtil;

    @Override
    public AdminResponseDto registerAdmin(AdminRequestDto adminRequestDto) throws Exception {

        List<Admin> admin=adminRepository.findAll();


        System.out.println(adminRequestDto.getPassword());
        System.out.println(adminRequestDto.getConfirmPassword());
        if(admin.size()!=0) throw new AdminRegistrationException("Your not authorised");
        if(adminRequestDto.getPassword().equals(adminRequestDto.getConfirmPassword())!=true){
            throw new Exception("Please Match your password");
        }
        Admin admin1= AdminRequestTOAdminTransformer.adminRequestDtoTAdmin(adminRequestDto);
        Admin admin2 =adminRepository.save(admin1);



        AdminResponseDto adminResponseDto=new AdminResponseDto();
        adminResponseDto.setName(admin2.getName());
        adminResponseDto.setEmail(admin2.getEmail());
        return adminResponseDto;
    }

    @Override
    public AdminResponseDto adminLogin(AdminRequestDto adminRequestDto) throws AdminRegistrationException {

        Admin admin=adminRepository.findByEmail(adminRequestDto.getEmail());
        if(admin==null){
            throw new AdminRegistrationException("Unauthorized User..!");
        }

        if(!admin.getPassword().equals(adminRequestDto.getPassword())){
            throw new AdminRegistrationException("Password does not match");
        }
        AdminResponseDto adminResponseDto=new AdminResponseDto();
        adminResponseDto.setName(admin.getName());
        adminResponseDto.setEmail(admin.getEmail());

        List<User> userList=userRepository.findAll();

        List<UserResponseDto> userResponseDtoList=new ArrayList<>();
        for(int i=0;i<userList.size();i++){
            UserResponseDto userResponseDto=new UserResponseDto();
            userResponseDto.setName(userList.get(i).getName());
            userResponseDto.setEmail(userList.get(i).getEmail());
            if(userList.get(i).isActive()){
                userResponseDto.setUserType("Verified");
            }else{
                userResponseDto.setUserType("Not Verified");
            }
            userResponseDtoList.add(userResponseDto);
        }
        System.out.println(userResponseDtoList.size());
        adminResponseDto.setUserResponseDtoList(userResponseDtoList);
        return adminResponseDto;
    }

  /*  @Override
    public AdminResponseDto activatingAdmin(EmailOtpRequestDto emailOtpRequestDto) throws Exception {


        AdminResponseDto adminResponseDto=new AdminResponseDto();
        adminResponseDto.setName(admin.getName());
        adminResponseDto.setEmail(admin.getEmail());
        adminRepository.save(admin);
        return adminResponseDto;
    }*/
}
