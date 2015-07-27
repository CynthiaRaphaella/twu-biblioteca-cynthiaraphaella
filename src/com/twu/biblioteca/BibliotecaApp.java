package com.twu.biblioteca;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.expections.BookIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidBookException;
import com.twu.biblioteca.expections.InvalidMenuException;
import com.twu.biblioteca.util.MessagesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    Scanner console;

    private List<Book> books = new ArrayList<Book>();
    private List<String> menu = new ArrayList<String>();

    private String LIST_BOOKS = "1. List all books";
    private String CHECKOUT_BOOK = "2. Get a book";
    private String RETURN_BOOK = "3. Return a book";
    private String QUIT_OPTION = "4. Quit";

    public BibliotecaApp(){
    }

    public String loadBibliotecaApplication(){
        seedMenu();
        seedPreExistingBooks();
        return MessagesUtil.WELCOME_MESSAGE;
    }

    public void startBiblioteca(){
        String message = loadBibliotecaApplication();
        print(message);

        console = new Scanner(System.in);
        int nextOption = 0;

        while (nextOption != 4){
            printMenu();
            nextOption = console.nextInt();
            try {
                chooseMenuOption(nextOption);
            } catch (InvalidMenuException e) {
                print(e.getMessage());
            }
        }
    }

    private void printMenu(){
        print("");
        print(MessagesUtil.MENU_WELCOME_MESSAGE);
        for(String item: menu){
            print(item);
        }
    }

    private void seedMenu() {
        menu.add(LIST_BOOKS);
        menu.add(CHECKOUT_BOOK);
        menu.add(RETURN_BOOK);
        menu.add(QUIT_OPTION);
    }

    public List<Book> seedPreExistingBooks(){
        List<Book> preExistingBooks = getPreExistingBooks();
        for(Book preExisting: preExistingBooks){
            books.add(preExisting);
        }
        return books;
    }

    public void chooseMenuOption(int option) throws InvalidMenuException {
        int menuOption;
        if(option > menu.size() || option < 1) {
            throw new InvalidMenuException();
        }
        else{
            menuOption = option - 1;
            if(menu.get(menuOption).equals(LIST_BOOKS)){
                printAllAvailableBooks();
            }
            else if(menu.get(menuOption).equals(CHECKOUT_BOOK)){
                print(MessagesUtil.CHOOSE_BOOK_CHECKOUT);
                printAllAvailableBooks();
                int bookCode = console.nextInt();
                try {
                    print(checkoutBook(bookCode));
                } catch (BookIsNotAvailableException e) {
                    print(e.getMessage());
                }
            }
            else if(menu.get(menuOption).equals(RETURN_BOOK)){
                print(MessagesUtil.CHOOSE_BOOK_RETURN);
                int bookCode = console.nextInt();
                try {
                    print(returnBook(bookCode));
                } catch (InvalidBookException e) {
                    print(e.getMessage());
                }
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
                book.isAvailable = true;
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

    public void printAllAvailableBooks(){
        print(MessagesUtil.LIST_ALL_BOOKS_MESSAGE);
        for(Book book: books){
            if(book.isAvailable){
                print(book.id + ". " + book.name);
            }
        }
        print("***********************************");
    }

    public List<String> getMenu(){
        return menu;
    }

    private void print(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.startBiblioteca();
    }
}
