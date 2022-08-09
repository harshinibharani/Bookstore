package com.example.Bookstoredb.bookstore.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(){
        return bookRepository.findAllByOrderByBookIDAsc();
    }

    public Set<String> getGenre(){
        Set<String> genres = new HashSet<String>();
        List<Book> book = this.getBooks();
        for (Book value : book) {
           // assert genres != null;
            genres.add(value.getGenre());
        }
        return genres;
    }

    public Set<String> getAuthors(){
        Set<String> authors = new HashSet<String>();
        List<Book> book = this.getBooks();
        for (Book value : book) {
            authors.add(value.getAuthor());
        }
        return authors;
    }

    public List<Book> getBookByGenre(String genre){
        return bookRepository.findByGenre(genre);
    }
    public List<Book> getBookByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBookByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public void deleteBook(Long bookID){
        bookRepository.deleteById(bookID);
    }
}
