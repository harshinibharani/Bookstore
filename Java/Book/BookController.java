package com.example.Bookstoredb.bookstore.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path = "api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/getbooks")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books = bookService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "/getbooks/getgenre")
    public Set<String> getGenre(){
        return bookService.getGenre();
    }

    @GetMapping(value = "/getbooks/getauthor")
    public Set<String> getAuthor(){
        return bookService.getAuthors();
    }

    @GetMapping(value = "/getbooks/genre/{genre}")
    public List<Book> getBookByGenre(@PathVariable("genre") String genre){
        return bookService.getBookByGenre(genre);
    }

    @GetMapping(value = "/getbooks/author/{author}")
    public List<Book> getBookByAuthor(@PathVariable("author") String author){
        return bookService.getBookByAuthor(author);
    }

    @GetMapping(value = "/getbooks/title/{title}")
    public List<Book> getBookByTitle(@PathVariable("title") String title){
        return bookService.getBookByTitle(title);
    }

    @GetMapping(value = "/getbooks/{input:[A-Za-z\\s]+}")
    public Set<Book> getRegexTitle(@PathVariable("input") String input){
        Set<Book> result = new HashSet<>();
        List<Book> books = bookService.getBooks();
        String[] keys=input.split(" ");
        for (String key : keys) {
            Pattern pattern = Pattern.compile(key.toLowerCase());
            for (Book book : books) {
                Matcher matcher = pattern.matcher(book.getTitle().toLowerCase());
                Matcher matcher1 = pattern.matcher(book.getGenre().toLowerCase());
                Matcher matcher2 = pattern.matcher(book.getAuthor().toLowerCase());
                if (matcher.find() || matcher1.find() || matcher2.find()) {
                    result.add(book);
                }
            }
        }
        return result;
    }

    @PostMapping(value = "/addbook")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @DeleteMapping(value = "/deletebook/{id}")
    public void deleteBook(@PathVariable("id") Long bookID){
        bookService.deleteBook(bookID);
    }
}
