package com.example.bookshopbot.entity;

import com.example.bookshopbot.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users extends AbstractEntity {

    private String chatId;
    private String name;
    private String username;
    private String phoneNumber;
    private String city;

    @OneToMany(mappedBy = "user")
    private List<Comments> comments;

    @OneToMany(mappedBy = "user")
    private List<BoughtBooks> boughtBooks;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
