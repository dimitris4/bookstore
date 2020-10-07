package com.example.demo.Controller;

import com.example.demo.Repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    /*//This is a placeholder that will act as a database
    ArrayList<Book> inventory = new ArrayList<Book>(Arrays.asList(
            new Book("Peter Pedal i zoologisk have",2020,true),
            new Book("1984",1949,false),
            new Book("Hærværk",1930,false)
    ));

    @GetMapping("/")
    public String bookListxxx(com.example.demo.Model model) {
        model.addAttribute("books", inventory);
        return "booklist";
    }


    @GetMapping("/newbook")
    public String newbook(com.example.demo.Model model) {
        model.addAttribute("books", inventory);
        return "newbook";
    }

    @PostMapping("/addToList")
    public String bookList(WebRequest request) {
        String param1 = request.getParameter("title");
        String param2 = request.getParameter("year");
        String param3 = request.getParameter("ebook");
        Book book = new Book(param1, Integer.parseInt(param2), Boolean.parseBoolean(param3));
        inventory.add(book);
        return"redirect:/";
    }


    @GetMapping("/xx")
    public String anything() {
        return "index";
    }

    @GetMapping("/xxy")
    public String anythingxxy() {
        return "index2";
    }*/

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String anyThing() {
        return "index";
    }

    @RequestMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books/list";
        //return model;
    }


    @GetMapping("/xxy")
    public String anythingxy() {
        return "index2";
    }

}


