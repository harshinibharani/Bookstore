package com.example.Bookstoredb.bookstore.cartitems;

import com.example.Bookstoredb.bookstore.books.Book;
import com.example.Bookstoredb.bookstore.books.BookRepository;
import com.example.Bookstoredb.bookstore.customer.Customer;
import com.example.Bookstoredb.bookstore.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CartItemsService {

    private final CartItemRepository cartItemRepository;

    private final BookRepository bookRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public CartItemsService(CartItemRepository cartItemRepository, BookRepository bookRepository, CustomerRepository customerRepository) {
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    public List<CartItems> listItems(Long customerId){
        return cartItemRepository.findByCustomerCustomerID(customerId);
    }

    public void addItem(Long bookId,Integer quantity,Long customerId){
        CartItems cartItem = new CartItems();
        Book book  = bookRepository.findById(bookId).get();
     //   if (book.getInStock()>0) {
            book.setInStock(book.getInStock() - 1);
            Customer customer = customerRepository.findById(customerId).get();
            cartItem.setQuantity(quantity);
            cartItem.setCustomer(customer);
            cartItem.setBook(book);
            bookRepository.save(book);
            cartItemRepository.save(cartItem);
    //    }
    }

    public void updateQuantity(Integer Qty,Long bookId,Long customerId){
        Customer customer = customerRepository.findById(customerId).get();
        Book book  = bookRepository.findById(bookId).get();
        CartItems cartItem = cartItemRepository.findByCustomerAndBook(customer,book);
        cartItemRepository.updateQuantity(Qty,bookId,customerId);
        book.setInStock(book.getInStock()-1);
        bookRepository.save(book);
        cartItemRepository.save(cartItem);
    }

    public void deleteItem(Long customerId,Long bookId){
        Customer customer = customerRepository.findById(customerId).get();
        Book book  = bookRepository.findById(bookId).get();
        CartItems cartItem = cartItemRepository.findByCustomerAndBook(customer,book);
        book.setInStock(cartItem.getQuantity()+book.getInStock());
        bookRepository.save(book);
        cartItemRepository.deleteByCustomerAndBook(customerId,bookId);
    }

    public void deleteCart(Long customerId,String action){
        List<CartItems> cartItems = cartItemRepository.findByCustomerCustomerID(customerId);
        for (CartItems cartItem : cartItems) {
            if (action=="cancel") {
                Book book = cartItem.getBook();
                book.setInStock(cartItem.getQuantity() + book.getInStock());
                bookRepository.save(book);
            }
            else {
                cartItemRepository.delete(cartItem);
            }
        }

    }
}
