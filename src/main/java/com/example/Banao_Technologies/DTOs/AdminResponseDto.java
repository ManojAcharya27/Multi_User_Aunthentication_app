package com.example.Banao_Technologies.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AdminResponseDto {

    String name;

    String email;

    List<UserResponseDto> userResponseDtoList;
}
