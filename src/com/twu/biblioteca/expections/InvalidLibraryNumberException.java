package com.twu.biblioteca.expections;

public class InvalidLibraryNumberException extends Exception{
    public InvalidLibraryNumberException(){
        super("Invalid format for library number. The format must be xxx-xxxx");
    }
}
