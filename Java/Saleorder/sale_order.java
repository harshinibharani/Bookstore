package com.example.Bookstoredb.bookstore.Saleorder;

import com.example.Bookstoredb.bookstore.SaleOrderLine.Saleorderline;
import com.example.Bookstoredb.bookstore.customer.Customer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sale_order")
public class sale_order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private float order_total;

    private String order_status;

    @Column(
            name = "order_date"
    )
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrder_total(float order_total) {
        this.order_total = order_total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public float getOrder_total() {
        return order_total;
    }

    public sale_order() {
        this.order_status = "In cart";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
