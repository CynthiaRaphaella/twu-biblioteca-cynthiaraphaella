package com.twu.biblioteca;

import com.twu.biblioteca.data.Book;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public List<Book> books = new ArrayList<Book>();

    public String startBibliotecaApplication(){
        return "Bem-vindo ao sistema de biblioteca";
    }

    public void addBookToBiblioteca(Book book){
        books.add(book);
    }

    public List<Book> showAllBooks(){
        return books;
    }

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
