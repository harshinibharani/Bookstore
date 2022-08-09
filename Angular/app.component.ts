import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Book } from './book';
import { BookService } from './book.service';
import { Cart } from './cart';
import { CartService } from './cart.service';
import { Customer } from './customer';
import { CustomerService } from './customer.service';
import { Global } from './customercart';
import { Order } from './order';
import { OrderLine } from './orderline';
import { OrderlineService } from './orderline.service';
import { Payment } from './payment';
import { PaymentService } from './payment.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public books: Book[]=[];
  public customers: Customer[]=[];
  public cart: Cart[]=[];
  public payment!:Payment;
  public order!:Order;
  public orderLine: OrderLine[]=[];
  public orderBooks: Book[]=[];
  public customerBook = Global.customerbook;
  public cust =  Global.cust;
  public total = 0;
  public Genre!: any[];
  public Books: Book[]=[];
  public Authors!: any[];


  constructor(private bookService: BookService,private customerService: CustomerService,private cartService: CartService,
    private paymentService: PaymentService,private orderLineService: OrderlineService){
  }

  ngOnInit(){
   // this.getBooks();
   this.getGenreAuthor();
    this.filterBooks();
    this.getCustomers();
  }

  public searchName(){
    let search =  (document.getElementById("csearch") as HTMLInputElement).value;
    this.customerService.searchName(search).subscribe(
      (response: Customer[]) => {
        this.customers = response;
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
    (document.getElementById("csearch") as HTMLInputElement).value = '';
  }

  public regexTitle(){
    let search =  (document.getElementById("rsearch") as HTMLInputElement).value;
    this.bookService.regexTitle(search).subscribe((response: any[]) =>{
      this.books=response;
     // this.Books=response;
    });
    document.getElementById('bookdiv')!.style.display = "block";
  }

  public getGenreAuthor(){
    this.bookService.getGenre().subscribe((response: any[]) =>{
      this.Genre=response;
    });
    this.bookService.getAuthor().subscribe((response: any[])=>{
      this.Authors=response;
    });
  }

  public filterBooks(){
    this.bookService.getBooks().subscribe(
      (response: Book[]) =>{
        this.Books=response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }

  public getCustomers(): void{

    this.customerService.getCustomers().subscribe(
      (response: Customer[]) => {
        this.customers = response;
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
  }

  public getOrders(){
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle','modal');
    button.setAttribute('data-target','#ordersModal');
    container?.appendChild(button);
    button.click();
    this.orderLineService.getOrders().subscribe(
      (response: OrderLine[]) => {
        this.orderLine=response;
       // this.orderBooks = response.book;
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
  }

  // public customerOrder(customerID: number){
  //   const container = document.getElementById('main-container');
  //   const button = document.createElement('button');
  //   button.type = 'button';
  //   button.style.display = 'none';
  //   button.setAttribute('data-toggle','modal');
  //   button.setAttribute('data-target','#ordersModal');
  //   container?.appendChild(button);
  //   button.click();
  //   this.orderLineService.getCustomerOrder(customerID).subscribe(
  //     (response: Book[]) => {
  //       this.orderBooks = response;
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //     }
  //   );
  // }

  public getAllBooks(){
    document.getElementById('bookdiv')!.style.display = "none";
    this.getBooks();
    document.getElementById('bookdiv')!.style.display = "block";
  }

  public getBooks(){
    this.bookService.getBooks().subscribe(
      (response: Book[]) =>{
        this.books = response;
        this.Books=response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }

  public bookByTitle(): void{
    document.getElementById('bookdiv')!.style.display = "none";
    let title =  (document.getElementById("input") as HTMLInputElement).value;
    this.bookService.getBookByTitle(title).subscribe(
      (response: Book[]) =>{
        this.books = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
    document.getElementById('bookdiv')!.style.display = "block";
    (document.getElementById("btitle") as HTMLInputElement).value='';
  }

  public bookByGenre(): void{
    document.getElementById('bookdiv')!.style.display = "none";
    let genre =  (document.getElementById("genre") as HTMLInputElement).value;
    this.bookService.getBookByGenre(genre).subscribe(
      (response: Book[]) =>{
        this.books = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
    document.getElementById('bookdiv')!.style.display = "block";
    (document.getElementById("genre") as HTMLInputElement).value = '';
  }

  public bookByAuthor(): void{
    document.getElementById('bookdiv')!.style.display = "none";
    let author =  (document.getElementById("author") as HTMLInputElement).value;
    this.bookService.getBookByAuthor(author).subscribe(
      (response: Book[]) =>{
        this.books = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
    document.getElementById('bookdiv')!.style.display = "block";
    (document.getElementById("author") as HTMLInputElement).value = '';
  }

  public onshowCart(customer: Customer){
    document.getElementById('nocart')!.style.display ="none";
    document.getElementById('cartdiv')!.style.display = "none";
    document.getElementById('addtocartdiv')!.style.display = "none";
    this.cust = customer.customerID;
    this.customerBook=customer;
    this.cartService.showCart(customer.customerID).subscribe(
      (response: Cart[]) =>{
        this.cart = response;
        if(!Object.keys(response).length ){
          document.getElementById('nocart')!.style.display ="block";
        }
        else{
          document.getElementById('cartdiv')!.style.display = "block";
        }
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }

  public getPayment(customer: Customer){
    this.paymentService.getPayment(customer.customerID).subscribe(
      (response: Payment) =>{
        this.payment = response;
       // this.order = response.order;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }

  public makePayment(paymentId: number,orderId: number,customerID: number){
    this.paymentService.makePayment(paymentId,orderId).subscribe(
      (response: Payment) =>{
        this.payment = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
    document.getElementById("pay_close")?.click();
    this.cartService.deleteCart(customerID,"pay").subscribe(
      (response:void) =>{
        document.getElementById("cartdiv")!.style.display = "none";
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }


  public cancelOrder(customerID: number,orderId: number){
    this.paymentService.cancelOrder(customerID,orderId).subscribe(
      (response:void) =>{
        document.getElementById("cartdiv")!.style.display = "none";
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
    this.cartService.deleteCart(customerID,"cancel").subscribe(
      (response:void) =>{
        document.getElementById("cartdiv")!.style.display = "none";
        this.getBooks();
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
    document.getElementById("pay_close")?.click();
  }

  public showBooks(customer: Customer){
    this.getBooks();
    document.getElementById('addtocartdiv')!.style.display = "block";
    this.cust = customer.customerID;
    this.customerBook = customer;
  }

 public onAddToCart(book: Book,quantity: number){
  this.cartService.addToCart(this.cust,book.bookID,quantity).subscribe(
    (response: Cart) =>{
      this.onshowCart(this.customerBook);
    },
    (error: HttpErrorResponse)=>{
      alert(error.message);
    }
  );
 }

 public updateCart(book: Book,customer: Customer,quantity: number){
   quantity = quantity+1;
   this.cartService.updateQuantity(customer.customerID,book.bookID,quantity).subscribe(
    (response: Cart) =>{
      this.onshowCart(customer);
    },
    (error: HttpErrorResponse)=>{
      alert(error.message);
    }
   );
 }

public onDeleteItem(customer: Customer,book: Book): void{
  this.cartService.deleteItem(customer.customerID,book.bookID).subscribe(
    (response: void) =>{
      this.onshowCart(customer);
    },
    (error: HttpErrorResponse) =>{
      alert(error.message);
    }
  );
}


  public onAddBook(addForm:NgForm): void{
    document.getElementById('close')?.click();
    this.bookService.addBook(addForm.value).subscribe(
      (response: Book) =>{
        //console.log(response);
        this.getBooks();
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
    this.getGenreAuthor();
  }


  public onDeleteBook(book: Book): void{
    //bookID = Number(bookID);
    this.bookService.deleteBook(book.bookID).subscribe(
      (response: void) =>{
       // console.log(response);
        this.getBooks();
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
    this.getGenreAuthor();
  }

  public onAddCustomer(addCustomerForm:NgForm): void{
    document.getElementById('cust-close')?.click();
    this.customerService.addCustomer(addCustomerForm.value).subscribe(
      (response: Customer) =>{
        console.log(response);
        this.getCustomers();
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
  }



  public onPayment(){
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle','modal');
  //  if(mode === 'payment'){
      button.setAttribute('data-target','#paymentModal');
   // }
    container?.appendChild(button);
    button.click();
    this.getPayment(this.customerBook);
  }

  public onOpenModal(mode: string): void
  {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle','modal');
    if(mode === 'add'){
      button.setAttribute('data-target','#addBookModal');
    }
    if(mode === 'addcustomer'){
      button.setAttribute('data-target','#addCustomerModal');
    }
    
    if(mode === 'viewcart'){
      button.setAttribute('data-toggle','#showCartModal');
    }
    container?.appendChild(button);
    button.click();
  }
}
