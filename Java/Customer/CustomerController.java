package com.example.Bookstoredb.bookstore.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/showcustomers")
    public List<Customer> showCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping(value = "/addcustomer")
    public void addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }

    @GetMapping(value = "/{name}")
    public List<Customer> searchName(@PathVariable String name){
        return customerService.searchName(name);
    }
}
