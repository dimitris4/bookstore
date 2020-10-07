package com.example.demo.Bootstrap;

import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.Model.Publisher;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        Author jens = new Author("Jens", "Larsen");
        Book mermaid = new Book("Little Mermaid", "33242342");
        jens.getBooks().add(mermaid);
        mermaid.getAuthors().add(jens);
        mermaid.setPublisher(publisher);
        publisher.getBooks().add(mermaid);

        authorRepository.save(jens);
        bookRepository.save(mermaid);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "332432432");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publisher: " + publisherRepository.count());
        System.out.println("Publiser number of books " + publisher.getBooks().size());
        System.out.println("End in com.example.demo.Bootstrap");

        //var pp = publisherRepository.findById(Long.valueOf(1));
        //var bok = pp.get().getBooks();
        //System.out.println("publiser book count= " + bok);
        //System.out.println("Publisher 1 books " + publisherRepository);


    }
}

