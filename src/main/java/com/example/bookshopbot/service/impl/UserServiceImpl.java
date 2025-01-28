package com.example.bookshopbot.service.impl;

import com.example.bookshopbot.dto.UserDto;
import com.example.bookshopbot.entity.Users;
import com.example.bookshopbot.repository.UserRepository;
import com.example.bookshopbot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;


    @Override
    public UserDto save(UserDto userDto) {
        return mapper.map(userRepository.save(mapper.map(userDto, Users.class)), UserDto.class);
    }
}
