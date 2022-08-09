package com.example.Bookstoredb.bookstore.Saleorder;

import com.example.Bookstoredb.bookstore.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleorderRepository extends JpaRepository<sale_order,Long> {

    sale_order findByIdAndCustomer(Long orderId,Customer customer);
    //sale_order findByCustomerAndDate(Customer customer, LocalDate date);

    boolean existsByCustomer(Customer customer);
    List<sale_order> findByCustomer(Long customer_id);
}
