package com.twu.biblioteca.expections;

public class ItemIsNotAvailableException extends Exception{

    public ItemIsNotAvailableException(){
        super("That item is not available");
    }
}
