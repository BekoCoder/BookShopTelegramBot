package com.example.bookshopbot.service;

import com.example.bookshopbot.dto.BooksDto;

public interface BooksService {
    BooksDto save(BooksDto booksDto);

    BooksDto update(Long id, BooksDto booksDto);

    void delete(Long id);

    BooksDto findByTitle(String title);
}
