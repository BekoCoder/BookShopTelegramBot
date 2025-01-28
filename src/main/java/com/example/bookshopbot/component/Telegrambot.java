package com.example.bookshopbot.component;

import com.example.bookshopbot.dto.UserDto;
import com.example.bookshopbot.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class Telegrambot extends TelegramLongPollingBot {

    private final UserService userService;

    @Override
    public void onUpdateReceived(Update update) {
        User user = update.getMessage().getFrom();
        UserDto userDto = new UserDto();
        if (update.hasMessage()) {
            if (update.getMessage().hasContact()) {
                userDto.setPhoneNumber(update.getMessage().getContact().getPhoneNumber());
                userDto.setChatId(update.getMessage().getChatId().toString());
                userDto.setName(update.getMessage().getContact().getFirstName());
                userDto.setUsername(update.getMessage().getContact().getLastName());

                userDto.setUsername(user.getUserName());
                userService.save(userDto);
                SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText("Rahmat! Muvaffaqiyatli ro'yxatdan o'tdingiz");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (update.getMessage().hasText()) {
                if (update.getMessage().getText().equals("/start")) {
                    sendContactRequest(update.getMessage().getChatId().toString());
                }
            }
        }
    }


    private void sendContactRequest(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Iltimos telefon raqamingizni kiriting");
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        KeyboardButton button = new KeyboardButton();
        button.setText("\uD83D\uDCF1 Telefon raqamni yuborish");
        button.setRequestContact(true);

        KeyboardRow row = new KeyboardRow();
        row.add(button);
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public String getBotUsername() {
        return "testlabcom_bot";
    }

    public Telegrambot(TelegramBotsApi telegramBotsApi, UserService userService) throws TelegramApiException {
        super("7329664870:AAH7L4-lzwTCmtYC01UmBNu0bHD9ofAdf0Q");
        this.userService = userService;
        telegramBotsApi.registerBot(this);
    }
}
