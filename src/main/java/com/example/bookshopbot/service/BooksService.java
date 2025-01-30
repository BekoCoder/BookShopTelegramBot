package com.example.bookshopbot.service;

import com.example.bookshopbot.dto.BooksDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BooksService {
    BooksDto save(BooksDto booksDto);

    BooksDto update(Long id, BooksDto booksDto);

    void delete(Long id);

    BooksDto findById(Long id);

    Page<BooksDto> getAll(Pageable pageable);
}
