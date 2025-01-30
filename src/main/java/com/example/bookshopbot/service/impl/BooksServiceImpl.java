package com.example.bookshopbot.service.impl;

import com.example.bookshopbot.dto.BooksDto;
import com.example.bookshopbot.entity.Books;
import com.example.bookshopbot.exceptions.BooksNotFoundException;
import com.example.bookshopbot.repository.BooksRepository;
import com.example.bookshopbot.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {
    private final BooksRepository booksRepository;
    private final ModelMapper mapper;


    @Override
    public BooksDto save(BooksDto booksDto) {
        Books books = mapper.map(booksDto, Books.class);
        if(Objects.isNull(booksDto) || isBookExist(booksDto.getTitle())) {
            throw new BooksNotFoundException("Kitob allaqachon mavjud !!!");
        }
        return mapper.map(booksRepository.save(books), BooksDto.class);

    }

    @Override
    public BooksDto update(Long id, BooksDto booksDto) {
        Books books = booksRepository.findById(id).orElseThrow(() -> new BooksNotFoundException("Kitob topilmadi !!!"));
        if(Objects.isNull(booksDto)){
            throw new BooksNotFoundException("Kitob topilmadi !!!");
        }
        books.setTitle(booksDto.getTitle());
        books.setAuthor(booksDto.getAuthor());
        books.setPrice(booksDto.getPrice());
        books.setQuantity(booksDto.getQuantity());
        return mapper.map(booksRepository.save(books), BooksDto.class);
    }

    @Override
    public void delete(Long id) {
        Books books = booksRepository.findById(id).orElseThrow(() -> new BooksNotFoundException("Kitob topilmadi !!!"));
        booksRepository.delete(books);
    }

    @Override
    public BooksDto findById(Long id) {
        Books books = booksRepository.findById(id).orElseThrow(() -> new BooksNotFoundException("Kitob topilmadi !!!"));
        if(Objects.isNull(books)){
            throw new BooksNotFoundException("Kitob topilmadi !!!");
        }
        return mapper.map(books, BooksDto.class);
    }

    @Override
    public Page<BooksDto> getAll(Pageable pageable) {
        return booksRepository.findAll(pageable).map(books -> mapper.map(books, BooksDto.class));
    }


    private boolean isBookExist(String title) {
        return booksRepository.findByTitle(title).isPresent();
    }
}
