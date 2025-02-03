package com.example.bookshopbot.dto;

import com.example.bookshopbot.enumeration.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private String chatId;
    private String name;
    private String username;
    private String phoneNumber;
    private String city;


    private UserRole userRole;
}
