package com.example.springsecurity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class MyUser{
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String roles;
}
