import { Customer } from "./customer";
import { Order } from "./order";

export interface Payment{
    id: number;
    customer: Customer;
    order: Order;
    status: string;
}