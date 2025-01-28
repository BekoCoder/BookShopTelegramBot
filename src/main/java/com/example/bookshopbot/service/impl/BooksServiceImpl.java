package com.example.bookshopbot.service.impl;

import com.example.bookshopbot.dto.BooksDto;
import com.example.bookshopbot.service.BooksService;
import org.jvnet.hk2.annotations.Service;

@Service
public class BooksServiceImpl implements BooksService {
    @Override
    public BooksDto save(BooksDto booksDto) {
        return null;
    }

    @Override
    public BooksDto update(Long id, BooksDto booksDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public BooksDto findByTitle(String title) {
        return null;
    }
}
