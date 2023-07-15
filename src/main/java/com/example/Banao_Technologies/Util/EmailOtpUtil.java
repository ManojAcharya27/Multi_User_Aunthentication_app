package com.example.Banao_Technologies.Util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailOtpUtil {

    public String otpGenerate(){
        Random random=new Random();
        int randomNumber=random.nextInt(9999);
        String number=Integer.toString(randomNumber);
        while(number.length()<4){
            number="0"+number;
        }
        return number;
    }
}
