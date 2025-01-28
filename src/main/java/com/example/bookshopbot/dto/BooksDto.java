package com.example.bookshopbot.dto;

import lombok.Data;

@Data
public class BooksDto {
    private String title;
    private String author;
    private String description;
    private Double price;
    private Integer quantity;
}
