package com.example.Banao_Technologies.Service.ServiceInterface;

import com.example.Banao_Technologies.DTOs.AdminRequestDto;
import com.example.Banao_Technologies.DTOs.AdminResponseDto;
import com.example.Banao_Technologies.DTOs.EmailOtpRequestDto;
import com.example.Banao_Technologies.Exceptions.AdminRegistrationException;
import jakarta.mail.MessagingException;

public interface AdminServiceInterface {


    public AdminResponseDto registerAdmin(AdminRequestDto adminRequestDto) throws Exception;


    public AdminResponseDto adminLogin(AdminRequestDto adminRequestDto) throws AdminRegistrationException;


   // public AdminResponseDto activatingAdmin(EmailOtpRequestDto emailOtpRequestDto) throws Exception;
}
