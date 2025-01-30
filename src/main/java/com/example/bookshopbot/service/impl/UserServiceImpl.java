package com.example.bookshopbot.service.impl;

import com.example.bookshopbot.dto.UserDto;
import com.example.bookshopbot.entity.Users;
import com.example.bookshopbot.enumeration.UserRole;
import com.example.bookshopbot.exceptions.UserNotFoundException;
import com.example.bookshopbot.repository.UserRepository;
import com.example.bookshopbot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;


    @Override
    public UserDto save(UserDto userDto) {
        Users users = mapper.map(userDto, Users.class);
//        if (isUserExist(users.getUsername())) {
//            throw new UserNotFoundException("User already exist");
//        }
       return mapper.map(userRepository.save(users), UserDto.class);
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserDto getById(Long id) {
        return null;
    }

    @Override
    public void addAdmin(String phoneNumber, Long superAdminId, String chatId) {
        SendMessage message = new SendMessage();
        Users user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UserNotFoundException("Foydalanuvchi topilmadi"));
        if(user.getRole().equals(UserRole.SUPER_ADMIN)){
            user.setRole(UserRole.ADMIN);
            userRepository.save(user);
            message.setChatId(chatId);
            message.setText("Admin muvaffaqiyatli qo'shildi");
        }
    }


//    private boolean isUserExist(String username) {
//        return userRepository.findByUsername(username).isPresent();
//    }
}
