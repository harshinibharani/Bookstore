package com.example.Bookstoredb.bookstore.SaleOrderLine;

import com.example.Bookstoredb.bookstore.Saleorder.SaleorderRepository;
import com.example.Bookstoredb.bookstore.books.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/orderline")
public class SaleorderlineController {

    private final SaleorderlineRepository saleorderlineRepository;
    private final SaleorderlineService saleorderlineService;
    private SaleorderRepository saleorderRepository;

    @Autowired
    public SaleorderlineController(SaleorderlineRepository saleorderlineRepository, SaleorderlineService saleorderlineService, SaleorderRepository saleorderRepository) {
        this.saleorderlineRepository = saleorderlineRepository;
        this.saleorderlineService = saleorderlineService;
        this.saleorderRepository= saleorderRepository;
    }

    @GetMapping
    public List<Saleorderline> getOrderLine(){
        return saleorderlineRepository.findAll();
    }

    @GetMapping(value = "/books/{customer_id}")
    public List<Book> showBooks(@PathVariable Long customer_id){
        return saleorderlineService.showOrderBooks(customer_id);
    }
}
