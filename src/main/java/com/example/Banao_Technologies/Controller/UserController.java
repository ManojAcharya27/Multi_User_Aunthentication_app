package com.example.Banao_Technologies.Controller;

import com.example.Banao_Technologies.DTOs.EmailOtpRequestDto;
import com.example.Banao_Technologies.DTOs.UserRequestDto;
import com.example.Banao_Technologies.DTOs.UserResponseDto;
import com.example.Banao_Technologies.Service.ServiceInterface.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserInterface userInterface;

    @GetMapping("/getLoginPage")
    public String getLoginPage(Model model){
       model.addAttribute("loginRequest" , new UserRequestDto());
        return "login_page";
    }

    @GetMapping("/getRegisterPage")
    public  String getRegisterPage(Model model){
        model.addAttribute("registerRequest" ,new UserRequestDto());
        return "register_page";
    }

    @PostMapping("/addUser")
    public String registerUser(@ModelAttribute UserRequestDto userRequestDto,Model model){
        try {
            UserResponseDto userResponseDto=userInterface.registerUser(userRequestDto);
             return "welcome_page2";
        }catch (Exception e){
            String temp=e.getMessage();
            model.addAttribute("errorMessage",temp);
            return "userAlredyPresnt_page";
        }
    }

    @GetMapping("/verifyEmail")
    public  String verifyEmail(Model model){
        model.addAttribute("otpRequest",new EmailOtpRequestDto());
        return "verify_page";
    }

    @PostMapping ("/activating_User")
    public String activatingUser(@ModelAttribute EmailOtpRequestDto emailOtpRequestDto){
            try {
                UserResponseDto userResponseDto=userInterface.activatingUser(emailOtpRequestDto);
                return "youcangopack_page";
            }catch (Exception e){
                return "error_page";
            }
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserRequestDto userRequestDto ,Model model){
        try {
            UserResponseDto userResponseDto=userInterface.loginUser(userRequestDto);
            model.addAttribute( "userName",userResponseDto.getName());
            return "welcome_page";
        }catch (Exception e){
            return "redirect:/getLoginPage";
        }
    }
}
