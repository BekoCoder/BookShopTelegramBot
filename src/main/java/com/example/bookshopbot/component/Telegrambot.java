package com.example.bookshopbot.component;

import com.example.bookshopbot.dto.UserDto;
import com.example.bookshopbot.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
                chooseLanguage(update.getMessage().getChatId().toString());

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

    private void chooseLanguage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Tilni tanlang !!!");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        KeyboardButton button = new KeyboardButton();
        button.setText("O'zbekcha");
        KeyboardButton button1 = new KeyboardButton();
        button1.setText("Русский");
        KeyboardButton button2 = new KeyboardButton();
        button2.setText("English");
        KeyboardRow row = new KeyboardRow();
        row.add(button);
        row.add(button1);
        row.add(button2);
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


//    private void chooseLanguageInline(String chatId){
//        SendMessage message=new SendMessage();
//        message.setChatId(chatId);
//        message.setText("Tilni tanlang !!!");
//        InlineKeyboardMarkup inlineKeyboardMarkup=new InlineKeyboardMarkup();
//        InlineKeyboardButton button=new InlineKeyboardButton();
//        InlineKeyboardButton button1=new InlineKeyboardButton();
//        InlineKeyboardButton button2=new InlineKeyboardButton();
//        button.setText("O'zbekcha");
//        button.setCallbackData("uz");
//        button1.setText("Русский");
//        button1.setCallbackData("ru");
//        button2.setText("English");
//        button2.setCallbackData("en");
//        List<List<InlineKeyboardButton>> keyboard=new ArrayList<>();
//        List<InlineKeyboardButton> row=new ArrayList<>();
//        row.add(button);
//        row.add(button1);
//        row.add(button2);
//        keyboard.add(row);
//        inlineKeyboardMarkup.setKeyboard(keyboard);
//        message.setReplyMarkup(inlineKeyboardMarkup);
//        try {
//            execute(message);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
//    }


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
