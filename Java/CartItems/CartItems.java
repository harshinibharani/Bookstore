package com.example.Bookstoredb.bookstore.cartitems;

import com.example.Bookstoredb.bookstore.books.Book;
import com.example.Bookstoredb.bookstore.customer.Customer;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Integer quantity;

    @Transient
    private float subtotal;

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public CartItems() {
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public float getSubtotal(){
        return this.book.getPrice()*quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
