package com.example.bookshopbot.repository;

import com.example.bookshopbot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByChatId(String chatId);

    Users findByPhoneNumber(String phoneNumber);
}
