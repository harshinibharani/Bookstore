package com.example.Bookstoredb.bookstore.SaleOrderLine;

import com.example.Bookstoredb.bookstore.Saleorder.sale_order;
import com.example.Bookstoredb.bookstore.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleorderlineRepository extends JpaRepository<Saleorderline,Long> {

    Saleorderline findByBookAndOrder(Book book, sale_order order);

    void deleteByOrder(sale_order order);

    @Query("SELECT book FROM Saleorderline line WHERE line.order.id=?1")
    @Modifying
    List<Book> findByOrder(Long orderId);

}
