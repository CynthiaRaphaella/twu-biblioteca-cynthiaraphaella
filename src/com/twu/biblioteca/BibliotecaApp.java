package com.twu.biblioteca;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.expections.BookIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidBookException;
import com.twu.biblioteca.expections.InvalidMenuException;
import com.twu.biblioteca.util.MessagesUtil;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    private List<Book> books = new ArrayList<Book>();
    private List<String> menu = new ArrayList<String>();

    private String LIST_BOOKS = "1. Listar todos os livros";

    public BibliotecaApp(){
    }

    public String startBibliotecaApplication(){
        seedMenu();
        seedPreExistingBooks();
        return MessagesUtil.WELCOME_MESSAGE;
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

    public void chooseMenuOption(int option) throws InvalidMenuException {
        if(option > menu.size() || option < 1) {
            throw new InvalidMenuException();
        }
        else{
            if(menu.get(option).equals(LIST_BOOKS)){
                showAllAvailableBooks();
            }
        }
    }

    public String checkoutBook(int id) throws BookIsNotAvailableException {
        if(id < 1){
            throw new BookIsNotAvailableException();
        }
        for(Book book: books){
            if(book.id == id && book.isAvailable){
                book.isAvailable = false;
                return MessagesUtil.CHECKOUT_MESSAGE;
            }
        }
        throw new BookIsNotAvailableException();
    }

    public String returnBook(int id) throws InvalidBookException {
        for(Book book: books){
            if(book.id == id && !book.isAvailable){
                books.add(book);
                return MessagesUtil.RETURN_MESSAGE;
            }
        }
        throw new InvalidBookException();
    }

    public List<Book> getPreExistingBooks(){
        List<Book> preExistingBooks = new ArrayList<Book>();
        preExistingBooks.add(new Book(1, "Book 1", "Author", "1990"));
        preExistingBooks.add(new Book(2, "Book 2", "Author", "1996"));
        preExistingBooks.add(new Book(3, "Book 3", "Author", "2000"));
        return preExistingBooks;
    }

    public List<Book> getAvailableBooks(){
        List<Book> availableBooks = new ArrayList<Book>();
        for(Book book: books){
            if(book.isAvailable){
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public void showAllAvailableBooks(){
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
