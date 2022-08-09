package com.example.Bookstoredb.bookstore.cartitems;

import com.example.Bookstoredb.bookstore.books.Book;
import com.example.Bookstoredb.bookstore.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems,Long> {

     List<CartItems> findByCustomerCustomerID(Long customerId);

     List<CartItems> findByCustomer(Customer customer);
    CartItems findByCustomerAndBook(Customer customer, Book book);

    @Query("UPDATE CartItems c SET c.quantity=?1 WHERE c.book.id=?2 AND c.customer.id=?3")
    @Modifying
     void updateQuantity(Integer quantity,Long bookId,Long customerId);

    @Query("DELETE FROM CartItems c WHERE c.customer.id=?1 AND c.book.id=?2")
    @Modifying
     void deleteByCustomerAndBook(Long customerId,Long BookId);
}
