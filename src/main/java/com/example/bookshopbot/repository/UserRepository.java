package com.example.bookshopbot.repository;

import com.example.bookshopbot.dto.UserDto;
import com.example.bookshopbot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByChatId(String chatId);

    Users findByPhoneNumber(String phoneNumber);

    Optional<UserDto> findByUsername(String username);
}
