package com.twu.biblioteca;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.expections.InvalidMenuException;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    private static List<Book> books = new ArrayList<Book>();
    private List<String> menu = new ArrayList<String>();

    private String LIST_BOOKS = "1. Listar todos os livros";

    public BibliotecaApp(){
    }

    public String startBibliotecaApplication(){
        seedMenu();
        seedPreExistingBooks();
        return "Bem-vindo ao sistema de biblioteca";
    }

    private void seedMenu() {
        menu.add(LIST_BOOKS);
    }

    public List<Book> seedPreExistingBooks(){
        List<Book> preExistingBooks = getPreExistingBooks();
        for(Book preExisting: preExistingBooks){
            books.add(preExisting);
        }
        return books;
    }

    /*public void chooseMenuOption(int option){
        try {
            if(option > menu.size()) {
                throw new InvalidMenuException();
            }
            else{
                if(menu.get(option).equals(LIST_BOOKS)){
                    showAllBooks();
                }
            }
        }
        catch (InvalidMenuException e){
            System.out.println(e.getMessage());
        }
    }*/

    public void chooseMenuOption(int option) throws InvalidMenuException {
        if(option > menu.size()) {
            throw new InvalidMenuException();
        }
        else{
            if(menu.get(option).equals(LIST_BOOKS)){
                showAllBooks();
            }
        }
    }

    public List<Book> getPreExistingBooks(){
        List<Book> preExistingBooks = new ArrayList<Book>();
        preExistingBooks.add(new Book("Book 1", "Author", "1990"));
        preExistingBooks.add(new Book("Book 2", "Author", "1996"));
        return preExistingBooks;
    }

    public List<Book> getAllBooks(){
        return books;
    }

    public void showAllBooks(){
        for(Book book: books){
            System.out.println(book.name);
        }
    }

    public List<String> getMenu(){
        return menu;
    }

    public void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
