package com.example.Bookstoredb.bookstore.SaleOrderLine;

import com.example.Bookstoredb.bookstore.Saleorder.SaleorderRepository;
import com.example.Bookstoredb.bookstore.Saleorder.sale_order;
import com.example.Bookstoredb.bookstore.books.Book;
import com.example.Bookstoredb.bookstore.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleorderlineService {

    private final SaleorderlineRepository saleorderlineRepository;

    @Autowired
    public SaleorderRepository saleorderRepository;

    @Autowired
    public SaleorderlineService(SaleorderlineRepository saleorderlineRepository) {
        this.saleorderlineRepository = saleorderlineRepository;
    }

    public List<Book> showOrderBooks(Long customer_id){
        List<sale_order> order = saleorderRepository.findByCustomer(customer_id);
       // Long id = order.getId();
        List<Book> books = null;
        for (int i = 0; i < order.size(); i++) {
            books.addAll(saleorderlineRepository.findByOrder(order.get(i).getId()));
        }
        return books;
    }

}
