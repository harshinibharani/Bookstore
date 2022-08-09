import { Book } from "./book";
import { Order } from "./order";

export interface OrderLine{
    id: number;
    order: Order;
    book: Book;
}