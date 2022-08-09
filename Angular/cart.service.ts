import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cart } from './cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private apiURL = environment.apiURL;
  
  constructor(private http:HttpClient) { }

  public showCart(customerID: number):Observable<Cart[]>{
    return this.http.get<Cart[]>(`${this.apiURL}/api/customer/cart/${customerID}`)
  }

  public addToCart(customerID: number,bookID: number,quantity: number):Observable<Cart>{
    return this.http.post<Cart>(`${this.apiURL}/api/customer/cart/additem/${customerID}/${bookID}/${quantity}`,customerID);
  }

  public updateQuantity(customerID: number,bookID: number,quantity: number):Observable<Cart>{
    return this.http.put<Cart>(`${this.apiURL}/api/customer/cart/updateitem/${customerID}/${bookID}/${quantity}`,customerID);
  }

  public deleteItem(customerID: number,bookID: number):Observable<void>{
    return this.http.delete<void>(`${this.apiURL}/api/customer/cart/deleteitem/${customerID}/${bookID}`);
  }

  public deleteCart(customerID: number,action: string):Observable<void>{
    return this.http.delete<void>(`${this.apiURL}/api/customer/cart/deletecart/${customerID}/${action}`);
  }

}
