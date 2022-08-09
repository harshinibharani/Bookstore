package com.example.Bookstoredb.bookstore.books;

import javax.persistence.*;

@Entity
@Table(name = "Books")
public class Book {
    @Id
    @SequenceGenerator(
            name="book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long bookID;
    private String title;
    private String author;
    @Column(
            columnDefinition = "TEXT"
    )
    private String ISBN;
    private String genre;
    private Integer price;
    private Integer inStock;

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Integer getInStock() {
        return inStock;
    }

    public Book() {
        this.inStock = 5;
    }

    public Long getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getPrice() {
        return price;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
