package com.twu.biblioteca.expections;

public class InvalidItemException extends Exception{

    public InvalidItemException(){
        super("That is not a valid item to return.");
    }
}
