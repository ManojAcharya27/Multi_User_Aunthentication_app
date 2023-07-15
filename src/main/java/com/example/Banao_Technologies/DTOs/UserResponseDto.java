package com.example.Banao_Technologies.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class UserResponseDto {

    String name;

    String  email;

    String userType;
}
