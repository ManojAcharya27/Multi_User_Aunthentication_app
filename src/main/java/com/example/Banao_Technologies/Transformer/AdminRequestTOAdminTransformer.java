package com.example.Banao_Technologies.Transformer;

import com.example.Banao_Technologies.DTOs.AdminRequestDto;
import com.example.Banao_Technologies.Entity.Admin;

public class AdminRequestTOAdminTransformer {

    public static Admin  adminRequestDtoTAdmin(AdminRequestDto adminRequestDto){
        return Admin.builder().name(adminRequestDto.getName()).email(adminRequestDto.getEmail()).
        password(adminRequestDto.getPassword()).build();
    }
}
