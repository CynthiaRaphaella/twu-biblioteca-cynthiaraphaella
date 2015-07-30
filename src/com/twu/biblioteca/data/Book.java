package com.twu.biblioteca.data;

import com.twu.biblioteca.util.MessagesUtil;

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

    public String getReturnMessage(){
        return MessagesUtil.RETURN_BOOK_MESSAGE;
    }

    public String getCheckoutMessage(){
        return MessagesUtil.CHECKOUT_BOOK_MESSAGE;
    }

    public Class getType(){
        return this.getClass();
    }

}
