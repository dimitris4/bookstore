package com.example.demo.Model;

public class BookPojo {

    private Long Id;
    private String title;
    private String isbn;

    public BookPojo(Long id, String title, String isbn) {
        Id = id;
        this.title = title;
        this.isbn = isbn;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
