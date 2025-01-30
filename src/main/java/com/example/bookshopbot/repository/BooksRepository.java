package com.example.bookshopbot.repository;

import com.example.bookshopbot.dto.BooksDto;
import com.example.bookshopbot.entity.Books;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository  extends JpaRepository<Books, Long> {


    @Query("select b from Books b where b.title = :title")
    Optional<BooksDto> findByTitle(String title);
}
