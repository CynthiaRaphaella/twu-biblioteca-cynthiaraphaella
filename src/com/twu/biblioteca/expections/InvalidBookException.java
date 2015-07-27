package com.twu.biblioteca.expections;

public class InvalidBookException extends Exception{

    public InvalidBookException(){
        super("That is not a valid book to return.");
    }
}
