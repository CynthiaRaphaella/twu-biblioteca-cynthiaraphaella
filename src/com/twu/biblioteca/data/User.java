package com.twu.biblioteca.data;

import com.twu.biblioteca.expections.InvalidLibraryNumberException;

public class User {
    private String libraryNumber;
    private String password;

    public User(String libraryNumber, String password) throws InvalidLibraryNumberException {
        if(checkLibraryNumber(libraryNumber)){
            this.libraryNumber = libraryNumber;
            this.password = password;
        }
        else{
            throw new InvalidLibraryNumberException();
        }
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    private boolean checkLibraryNumber(String number){
        return number.matches("([0-9]{3})-([0-9]{4})");
    }
}
