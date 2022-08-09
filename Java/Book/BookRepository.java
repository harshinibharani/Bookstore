package com.example.Bookstoredb.bookstore.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findAllByOrderByBookIDAsc();
    List<Book> findByGenre(String genre);

  //  @Query(value = "SELECT * FROM Book b WHERE b.title LIKE '?1%'")
 //   @Modifying
    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);
}
