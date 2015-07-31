package com.twu.biblioteca.data;

import com.twu.biblioteca.expections.InvalidLibraryNumberException;

public class User extends ItemList {
    private String libraryNumber;
    private String password;
    private String name;
    private String email;
    private String address;
    private String phone;

    public User(String libraryNumber, String password, String name, String email, String address, String phone) throws InvalidLibraryNumberException {
        if(checkLibraryNumber(libraryNumber)){
            this.libraryNumber = libraryNumber;
            this.password = password;
            this.name = name;
            this.email = email;
            this.address = address;
            this.phone = phone;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    private boolean checkLibraryNumber(String number){
        return number.matches("([0-9]{3})-([0-9]{4})");
    }

    public String getPropertiesNames(){
        return SEPARATOR_ITEM + "Name" + SEPARATOR_ITEM + "E-mail" + SEPARATOR_ITEM + "Address" + SEPARATOR_ITEM + "Phone number" + SEPARATOR_ITEM;
    }

    public String getPropertiesValues(){
        return SEPARATOR_ITEM + getName() + SEPARATOR_ITEM + getEmail() + SEPARATOR_ITEM + getAddress() + SEPARATOR_ITEM + getPhone() + SEPARATOR_ITEM;
    }
}
