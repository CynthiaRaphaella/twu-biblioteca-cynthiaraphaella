package com.twu.biblioteca.data;

public class Book extends Item {

    private String author;

    public Book(int id, String name, String author, String year){
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.isAvailable = true;
    }

    public String getAuthor() {
        return author;
    }

}
