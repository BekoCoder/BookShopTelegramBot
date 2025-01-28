package com.example.bookshopbot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @OneToMany(mappedBy = "user")
    private List<Comments> comments;

    @OneToMany(mappedBy = "user")
    private List<BoughtBooks> boughtBooks;
}
