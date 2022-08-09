import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Customer } from './customer';
import { Order } from './order';
import { Payment } from './payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private apiURL = environment.apiURL;
  
  constructor(private http:HttpClient) { }

  public getPayment(customerID: number):Observable<Payment>{
    return this.http.post<Payment>(`${this.apiURL}/api/payment/${customerID}`,customerID);
  }

  public makePayment(paymentId: number,orderId: number):Observable<Payment>{
    return this.http.get<Payment>(`${this.apiURL}/api/payment/makepayment/${paymentId}/${orderId}`);
  }

  public cancelOrder(customerID: number,orderId: number):Observable<void>{
    return this.http.delete<void>(`${this.apiURL}/api/payment/delete/${customerID}/${orderId}`);
  }
}
