package com.twu.biblioteca;

import com.twu.biblioteca.data.Book;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    private static List<Book> books = new ArrayList<Book>();

    public BibliotecaApp(){
    }

    public String startBibliotecaApplication(){
        seedPreExistingBooks();
        return "Bem-vindo ao sistema de biblioteca";
    }

    public List<Book> seedPreExistingBooks(){
        List<Book> preExistingBooks = getPreExistingBooks();
        for(Book preExisting: preExistingBooks){
            books.add(preExisting);
        }
        return books;
    }

    public List<Book> getPreExistingBooks(){
        List<Book> preExistingBooks = new ArrayList<Book>();
        preExistingBooks.add(new Book("Book 1", "Author", "1990"));
        preExistingBooks.add(new Book("Book 2", "Author", "1996"));
        return preExistingBooks;
    }

    public List<Book> showAllBooks(){
        return books;
    }

    public void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
