package com.example.Bookstoredb.bookstore.SaleOrderLine;

import com.example.Bookstoredb.bookstore.books.Book;
import com.example.Bookstoredb.bookstore.Saleorder.sale_order;

import javax.persistence.*;

@Entity
@Table(name = "sale_order_line")
public class Saleorderline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private sale_order order;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Saleorderline() {
    }

    public Long getId() {
        return id;
    }

    public sale_order getOrder() {
        return order;
    }

    public Book getBook() {
        return book;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(sale_order order) {
        this.order = order;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
