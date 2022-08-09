import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Book } from './book';
import { OrderLine } from './orderline';

@Injectable({
  providedIn: 'root'
})
export class OrderlineService {

  private apiURL = environment.apiURL;

  constructor(private http:HttpClient) { }

  public getCustomerOrder(customerID: number):Observable<Book[]>{
    return this.http.get<Book[]>(`${this.apiURL}/api/orderline/books/${customerID}`);
  }

  public getOrders():Observable<OrderLine[]>{
    return this.http.get<OrderLine[]>(`${this.apiURL}/api/orderline`);
  }
}
