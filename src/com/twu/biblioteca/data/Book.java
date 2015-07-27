package com.twu.biblioteca.data;

public class Book {

    public int id;
    public String name;
    public String author;
    public String year;
    public boolean isAvailable;

    public Book(int id, String name, String author, String year){
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.isAvailable = true;
    }

}
