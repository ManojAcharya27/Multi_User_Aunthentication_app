package com.example.Banao_Technologies.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "admin")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Admin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    String email;

    String password;





}
