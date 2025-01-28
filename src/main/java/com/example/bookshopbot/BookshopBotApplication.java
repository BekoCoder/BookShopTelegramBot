package com.example.bookshopbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.bookshopbot")
public class BookshopBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookshopBotApplication.class, args);
    }

}
