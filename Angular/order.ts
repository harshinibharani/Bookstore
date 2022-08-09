import { Customer } from "./customer";

export interface Order{
    id: number;
    customer: Customer;
    order_status: string;
    order_total: number;
}