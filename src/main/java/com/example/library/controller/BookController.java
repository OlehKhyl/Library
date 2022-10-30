package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.BookTo;
import com.example.library.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    public List<Book> get(@RequestParam (required = false) String name,
                    @RequestParam (required = false) String author) {
        List<Book> list = repository.findAll();

        if (author != null) {
            list.retainAll(repository.findBookByAuthorContainingIgnoreCase(author));
        }
        if (name != null) {
            list.retainAll(repository.findBookByNameContainingIgnoreCase(name));
        }

        return list;
    }

    @GetMapping("/books/{id}")
    public Book getById(@PathVariable("id") int id) {
        return repository.findBookById(id);
    }

    @GetMapping("/books/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        repository.delete(repository.findBookById(id));
    }

    @PostMapping("/books")
    public void save(@RequestBody BookTo bookTo) {
        Book book = new Book(bookTo);
        repository.save(book);
    }

    @PostMapping("/books/update/{id}")
    public Book update(@PathVariable("id") int id,
                       @RequestBody BookTo bookTo) {
        Book book = repository.findBookById(id);
        book.setName(bookTo.getName());
        book.setAuthor(bookTo.getAuthor());
        repository.save(book);
        return book;
    }
}
