package com.example.bookshopbot.repository;

import com.example.bookshopbot.entity.BoughtBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoughtBooksRepository extends JpaRepository<BoughtBooks, Long> {


}
