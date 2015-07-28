package com.twu.biblioteca.services;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.expections.BookIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidBookException;
import com.twu.biblioteca.expections.InvalidMenuException;
import com.twu.biblioteca.util.MessagesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaManager {

    private List<Book> books = new ArrayList<Book>();
    private List<String> menu = new ArrayList<String>();

    public String loadBibliotecaApplication(){
        seedMenu();
        seedPreExistingBooks();
        return MessagesUtil.WELCOME_MESSAGE;
    }

    private void seedMenu() {
        menu.add(MessagesUtil.LIST_BOOKS_MENU);
        menu.add(MessagesUtil.CHECKOUT_BOOK_MENU);
        menu.add(MessagesUtil.RETURN_BOOK_MENU);
        menu.add(MessagesUtil.QUIT_OPTION_MENU);
    }

    public List<Book> seedPreExistingBooks(){
        List<Book> preExistingBooks = getPreExistingBooks();
        for(Book preExisting: preExistingBooks){
            books.add(preExisting);
        }
        return books;
    }

    public String checkoutBook(int id) throws BookIsNotAvailableException {
        if(id < 1){
            throw new BookIsNotAvailableException();
        }
        for(Book book: books){
            if(book.getId() == id && book.isAvailable()){
                book.checkoutBook();
                return MessagesUtil.CHECKOUT_MESSAGE;
            }
        }
        throw new BookIsNotAvailableException();
    }

    public String returnBook(int id) throws InvalidBookException {
        for(Book book: books){
            if(book.getId() == id && !book.isAvailable()){
                book.returnBook();
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
            if(book.isAvailable()){
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<String> getMenu(){
        return menu;
    }
}
