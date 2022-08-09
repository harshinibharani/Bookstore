package com.example.Bookstoredb.bookstore.payment;

import com.example.Bookstoredb.bookstore.Saleorder.sale_order;
import com.example.Bookstoredb.bookstore.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getAllPayments(){
        return paymentService.getAll();
    }

    @PostMapping(value = "/{customerId}")
    public Payment setPayment(@PathVariable Long customerId){
        return paymentService.getByCustomer(customerId);
    }

    @GetMapping(value = "/makepayment/{payment_id}/{order_id}")
    public Payment makePayment(@PathVariable Long payment_id,@PathVariable Long order_id){
        return paymentService.makePayment(payment_id,order_id);
    }

    @DeleteMapping(value = "/delete/{customer_id}/{order_id}")
    public void cancelOrder(@PathVariable Long customer_id, @PathVariable Long order_id){
        paymentService.cancelOrder(customer_id,order_id);
    }
}
