import { Book } from "./book";
import { Customer } from "./customer";

export interface Cart{
    book: Book;
    customer: Customer;
    quantity: number;
    subtotal: number;
}