package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findAll();
    List<Book> findBookByAuthorContainingIgnoreCase(String author);
    List<Book> findBookByNameContainingIgnoreCase(String name);
    Book findBookById(int id);
}
