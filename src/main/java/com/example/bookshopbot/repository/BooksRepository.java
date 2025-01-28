package com.example.bookshopbot.repository;

import com.example.bookshopbot.entity.Books;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository  extends JpaRepository<Books, Long> {

}
