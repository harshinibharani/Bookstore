import { Injectable } from '@angular/core';
import{ HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Book } from './book';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class BookService {

  private apiURL = environment.apiURL;

  constructor(private http:HttpClient) { }

  public getBooks():Observable<Book[]>{
    return this.http.get<Book[]>(`${this.apiURL}/api/books/getbooks`);
  }

  public regexTitle(title: string):Observable<any[]>{
    return this.http.get<any[]>(`${this.apiURL}/api/books/getbooks/${title}`);
  }

  public getGenre():Observable<any[]>{
    return this.http.get<any[]>(`${this.apiURL}/api/books/getbooks/getgenre`);
  }

  public getAuthor():Observable<any[]>{
    return this.http.get<any[]>(`${this.apiURL}/api/books/getbooks/getauthor`);
  }

  public getBookByGenre(genre: string):Observable<Book[]>{
    return this.http.get<Book[]>(`${this.apiURL}/api/books/getbooks/genre/${genre}`);
  }

  public getBookByAuthor(author: string):Observable<Book[]>{
    return this.http.get<Book[]>(`${this.apiURL}/api/books/getbooks/author/${author}`);
  }


  public getBookByTitle(title: string):Observable<Book[]>{
    return this.http.get<Book[]>(`${this.apiURL}/api/books/getbooks/title/${title}`);
  }

  public addBook(book: Book):Observable<Book>{
    return this.http.post<Book>(`${this.apiURL}/api/books/addbook`,book);
  }

  public deleteBook(bookID: number):Observable<void>{
    return this.http.delete<void>(`${this.apiURL}/api/books/deletebook/${bookID}`);
  }
}
