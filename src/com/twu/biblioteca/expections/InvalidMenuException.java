package com.twu.biblioteca.expections;

public class InvalidMenuException extends Exception{

    public InvalidMenuException(){
        super("Select a valid option!");
    }
}
