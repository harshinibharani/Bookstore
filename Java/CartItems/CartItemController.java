package com.example.Bookstoredb.bookstore.cartitems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/customer/cart")
public class CartItemController {

    private final CartItemsService cartItemsService;

    @Autowired
    public CartItemController(CartItemsService cartItemsService) {
        this.cartItemsService = cartItemsService;
    }

    @GetMapping(value = "/{customerId}")
    public List<CartItems> listItems(@PathVariable("customerId") Long id){
        return cartItemsService.listItems(id);
    }

    @PostMapping(value = "/additem/{customerId}/{bookId}/{qty}")
    public void addItem(@PathVariable Long customerId,@PathVariable("bookId") Long bookId,
                              @PathVariable("qty") Integer quantity){
        cartItemsService.addItem(bookId,quantity,customerId);
    }

    @PutMapping(value = "/updateitem/{customerId}/{bookId}/{qty}")
    public void updateQuantity(@PathVariable Long customerId,@PathVariable("bookId") Long bookId,
                               @PathVariable("qty") Integer quantity){

        cartItemsService.updateQuantity(quantity,bookId,customerId);

    }

    @DeleteMapping(value = "/deleteitem/{customerId}/{bookId}")
    public void deleteItem(@PathVariable Long customerId,@PathVariable("bookId") Long bookId){

        cartItemsService.deleteItem(customerId,bookId);
    }

    @DeleteMapping(value = "deletecart/{customerId}/{action}")
    public void deleteCart(@PathVariable Long customerId,@PathVariable String action){
        cartItemsService.deleteCart(customerId,action);
    }
}
