import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Customer } from './customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private apiURL = environment.apiURL;

  constructor(private http:HttpClient) { }

  public getCustomers():Observable<Customer[]>{
    return this.http.get<Customer[]>(`${this.apiURL}/api/customer/showcustomers`);
  }

  public addCustomer(customer: Customer):Observable<Customer>{
    return this.http.post<Customer>(`${this.apiURL}/api/customer/addcustomer`,customer);
  }
  
  public searchName(name: string):Observable<Customer[]>{
    return this.http.get<Customer[]>(`${this.apiURL}/api/customer/${name}`);
  }

}
