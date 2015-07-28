package com.twu.biblioteca.data;

public class Book {

    private int id;
    private String name;
    private String author;
    private String year;
    private boolean isAvailable;

    public Book(int id, String name, String author, String year){
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.isAvailable = true;
    }

    public void returnBook(){
        this.isAvailable = true;
    }

    public void checkoutBook(){
        this.isAvailable = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

}
