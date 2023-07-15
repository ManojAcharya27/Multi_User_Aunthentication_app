package com.example.Banao_Technologies.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AdminRequestDto {

    String name;

    String email;

    String password;

    String confirmPassword;


}
