package com.twu.biblioteca.expections;

public class BookIsNotAvailableException extends Exception{

    public BookIsNotAvailableException(){
        super("That book is not available");
    }
}
