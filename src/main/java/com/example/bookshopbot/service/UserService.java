package com.example.bookshopbot.service;

import com.example.bookshopbot.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.telegram.telegrambots.meta.api.objects.User;

public interface UserService {
    UserDto save(UserDto userDto);

    UserDto update(Long id, UserDto userDto);

    void delete(Long id);

    UserDto getById(Long id);

    void addAdmin(String phoneNumber, Long superAdminId, String chatId);

    Page<UserDto> getAll(Pageable pageable);
}
