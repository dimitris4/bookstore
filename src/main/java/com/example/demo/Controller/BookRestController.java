package com.example.demo.Controller;

import com.example.demo.Model.Book;
import com.example.demo.Model.BookPojo;
import com.example.demo.Repository.BookRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookRestController {

    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<BookPojo> findAll() {
        List<Book> books = new ArrayList<Book>();
        List<BookPojo> pojos = new ArrayList();
        bookRepository.findAll().forEach(book -> books.add(book));
        for (Book bb: books) {
            BookPojo bp = new BookPojo(bb.getId(), bb.getTitle(), bb.getIsbn());
            pojos.add(bp);
        }
        return pojos;
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookPojo> findBookById(@PathVariable long id) {
        var bok = bookRepository.findById(id);
        if (bok.isPresent()) {
            Book bkk = bok.get();
            BookPojo pjj = new BookPojo(bkk.getId(), bkk.getTitle(), bkk.getIsbn());
            return new ResponseEntity<>(pjj, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //Creates a new book. Can be called from postman.
    @PostMapping(value= "/books", consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Book postBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    //Update part of existing book already in database
    @PatchMapping(path="/books/{id}", consumes="application/json")
    public Book patchBook(@PathVariable long id, @RequestBody Book patch) {
        Book book = bookRepository.findById(id).get();
        if (patch.getTitle() != null) { book.setTitle(patch.getTitle()); }
        if (patch.getIsbn() != null) { book.setIsbn(patch.getIsbn()); }
        return bookRepository.save(book);
    }

    //Creates new book if new primary key (id not in database), otherwise update existing book in database
    @PutMapping("/books/{id}")
    public Book putBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable long id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {}
    }

}



