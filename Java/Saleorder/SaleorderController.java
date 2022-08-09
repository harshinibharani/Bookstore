package com.example.Bookstoredb.bookstore.Saleorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/saleorder")
public class SaleorderController {

    private final SaleorderService saleorderService;

    @Autowired
    public SaleorderController(SaleorderService saleorderService) {
        this.saleorderService = saleorderService;
    }


    @GetMapping
    public List<sale_order> getOrder(){
        return saleorderService.getOrder();
    }
}
