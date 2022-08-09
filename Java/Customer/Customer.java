package com.example.Bookstoredb.bookstore.customer;


import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @SequenceGenerator(
            name="customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long customerID;
    private String name;
    @Column(
            columnDefinition = "TEXT"
    )
    private String phno;

    public Customer() {
    }

    public Customer(Long customerID, String name, String phno) {
        this.customerID = customerID;
        this.name = name;
        this.phno = phno;
    }

    public Customer(String name, String phno) {
        this.name = name;
        this.phno = phno;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getPhno() {
        return phno;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
