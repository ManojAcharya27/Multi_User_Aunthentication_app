package com.example.Banao_Technologies.Controller;

import com.example.Banao_Technologies.DTOs.*;
import com.example.Banao_Technologies.Service.ServiceInterface.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {


    @Autowired
    AdminServiceInterface adminServiceInterface;


    @Autowired
    AdminServiceInterface adminServiceInterface1;


    @GetMapping("/getAdminLoginPage")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest" , new AdminRequestDto());
        return "admin_login_page";
    }

    @GetMapping("/getAdminRegisterPage")
    public  String getRegisterPage(Model model){
        model.addAttribute("registerRequest" ,new AdminRequestDto());
        return "admin_register_page";
    }


    @PostMapping("/addAdmin")
    public String registerAdmin(@ModelAttribute AdminRequestDto adminRequestDto, Model model){
        try {
             AdminResponseDto adminResponseDto= adminServiceInterface.registerAdmin(adminRequestDto);
            return "redirect:/getAdminLoginPage";
        }catch (Exception e){
            String temp=e.getMessage();
            model.addAttribute("errorMessage",temp);
            return "userAlredyPresnt_page";
        }
    }

    @PostMapping("/admin_login")
    public String adminLogin(@ModelAttribute AdminRequestDto adminRequestDto,Model model){
      //  System.out.println(adminRequestDto.getEmail());
        try {
            AdminResponseDto adminResponseDto=adminServiceInterface1.adminLogin(adminRequestDto);
            model.addAttribute("userList",adminResponseDto.getUserResponseDtoList());
            return "admin_welcome_page";
        }catch (Exception e){
             return "unauthorized_admin_page";
        }
    }
}
