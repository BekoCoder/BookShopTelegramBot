package com.example.bookshopbot.service;

import com.example.bookshopbot.dto.UserDto;

public interface UserService {
    UserDto save(UserDto userDto);

    UserDto update(Long id, UserDto userDto);

    void delete(Long id);

    UserDto findByChatId(String chatId);
}
