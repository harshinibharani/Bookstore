package com.example.Bookstoredb.bookstore.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    public Customer findByCustomerID(Long customerId);


    @Query(nativeQuery = true,value = "SELECT *,REGEXP_MATCHES(name,?1,'i') FROM customers")
    @Modifying
    List<Customer> findByName(String name);
}
