package com.example.Bookstoredb.bookstore.payment;
import com.example.Bookstoredb.bookstore.customer.Customer;
import com.example.Bookstoredb.bookstore.Saleorder.sale_order;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private sale_order order;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String status;

    @Column(
            name = "payment_date"
    )
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Payment() {
        this.status = "Not Paid";
    }

    public Long getId() {
        return id;
    }

    public sale_order getOrder() {
        return order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public void setOrder(sale_order order) {
        this.order = order;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
