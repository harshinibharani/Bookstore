package com.example.Bookstoredb.bookstore.payment;

import com.example.Bookstoredb.bookstore.SaleOrderLine.Saleorderline;
import com.example.Bookstoredb.bookstore.SaleOrderLine.SaleorderlineRepository;
import com.example.Bookstoredb.bookstore.Saleorder.SaleorderRepository;
import com.example.Bookstoredb.bookstore.Saleorder.sale_order;
import com.example.Bookstoredb.bookstore.books.Book;
import com.example.Bookstoredb.bookstore.cartitems.CartItemRepository;
import com.example.Bookstoredb.bookstore.cartitems.CartItems;
import com.example.Bookstoredb.bookstore.customer.Customer;
import com.example.Bookstoredb.bookstore.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SaleorderRepository saleorderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CartItemRepository cartItemRepository;

    @Autowired
    public SaleorderlineRepository saleorderlineRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, SaleorderRepository saleorderRepository, CustomerRepository customerRepository) {
        this.paymentRepository = paymentRepository;
        this.saleorderRepository = saleorderRepository;
        this.customerRepository = customerRepository;
    }

    public void setOrderLine(sale_order order, Book book){
        Saleorderline saleorderline = new Saleorderline();
        saleorderline.setOrder(order);
        saleorderline.setBook(book);
        saleorderlineRepository.save(saleorderline);
    }

    public Payment getByCustomer(Long customerId){
        Customer customer = customerRepository.findByCustomerID(customerId);
        if (paymentRepository.findByCustomer(customer)!=null && saleorderRepository.existsByCustomer(customer)){
            return paymentRepository.findByCustomer(customer);
        }
        Payment payment = new Payment();
        sale_order order = new sale_order();
        payment.setCustomer(customer);
        payment.setDate(LocalDateTime.now());
        order.setCustomer(customer);
        order.setDate(LocalDateTime.now());
        List<CartItems> cartItems = cartItemRepository.findByCustomerCustomerID(customerId);
        float sum = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            sum += cartItems.get(i).getSubtotal();
            setOrderLine(order, cartItems.get(i).getBook());
        }
        order.setOrder_total(sum);
        payment.setOrder(order);
        paymentRepository.save(payment);
        saleorderRepository.save(order);
        return payment;
    }

    public Payment makePayment(Long paymentId,Long orderId){
        //Customer customer = customerRepository.findByCustomerID(customerId);
        //sale_order order = saleorderRepository.findByIdAndCustomer(orderId,customer);
        Payment payment = paymentRepository.findByIdAndOrderId(paymentId,orderId);
        payment.setStatus("Paid");
        sale_order order = saleorderRepository.findByIdAndCustomer(orderId,payment.getCustomer());
        order.setOrder_status("Order Placed");
        payment.setDate(LocalDateTime.now());
        saleorderRepository.save(order);
        paymentRepository.save(payment);
        return payment;
    }

    public void cancelOrder(Long customerId,Long orderId){
        Customer customer = customerRepository.findByCustomerID(customerId);
        sale_order order = saleorderRepository.findByIdAndCustomer(orderId,customer);
        Payment payment = paymentRepository.findByCustomerAndOrder(customer,order);
        paymentRepository.delete(payment);
        saleorderlineRepository.deleteByOrder(order);
        saleorderRepository.delete(order);
    }
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }
}
