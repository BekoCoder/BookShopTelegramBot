package com.example.bookshopbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bought_books")
public class BoughtBooks extends AbstractEntity {
    private Double totalPrice;

    @JoinColumn(name = "users_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    @JoinColumn(name = "books_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Books book;
}
