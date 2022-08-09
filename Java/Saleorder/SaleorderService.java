package com.example.Bookstoredb.bookstore.Saleorder;

import com.example.Bookstoredb.bookstore.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleorderService {

    private final SaleorderRepository saleorderRepository;

    @Autowired
    public SaleorderService(SaleorderRepository saleorderRepository) {
        this.saleorderRepository = saleorderRepository;
    }

    public List<sale_order> getOrder(){
       return saleorderRepository.findAll();
    }


}
