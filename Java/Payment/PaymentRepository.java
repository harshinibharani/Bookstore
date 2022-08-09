package com.example.Bookstoredb.bookstore.payment;

import com.example.Bookstoredb.bookstore.Saleorder.sale_order;
import com.example.Bookstoredb.bookstore.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

  //  Payment findByOrder(sale_order order);
    Payment findByCustomer(Customer customer);

    Payment findByIdAndOrderId(Long paymentId,Long orderId);

  //  boolean exitsByCustomer(Customer customer);
    Payment findByCustomerAndOrder(Customer customer,sale_order order);
    boolean existsByCustomerAndOrder(Customer customer,sale_order order);
}
