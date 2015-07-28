package com.twu.biblioteca;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.expections.BookIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidBookException;
import com.twu.biblioteca.expections.InvalidMenuException;
import com.twu.biblioteca.services.BibliotecaManager;
import com.twu.biblioteca.util.MessagesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    Scanner console;
    BibliotecaManager bibliotecaManager;

    public BibliotecaApp(){
    }

    public BibliotecaApp(BibliotecaManager bibliotecaManager){
        this.bibliotecaManager = bibliotecaManager;
    }

    public void startBiblioteca(){
        bibliotecaManager = new BibliotecaManager();
        String message = bibliotecaManager.loadBibliotecaApplication();
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
        for(String item: bibliotecaManager.getMenu()){
            print(item);
        }
    }

    public void chooseMenuOption(int option) throws InvalidMenuException {
        int menuOption;
        List<String> menu = bibliotecaManager.getMenu();

        if(option > menu.size() || option < 1) {
            throw new InvalidMenuException();
        }
        else{
            menuOption = option - 1;
            if(menu.get(menuOption).equals(MessagesUtil.LIST_BOOKS_MENU)){
                printAllAvailableBooks();
            }
            else if(menu.get(menuOption).equals(MessagesUtil.CHECKOUT_BOOK_MENU)){
                print(MessagesUtil.CHOOSE_BOOK_CHECKOUT);
                printAllAvailableBooks();
                int bookCode = console.nextInt();
                try {
                    print(bibliotecaManager.checkoutBook(bookCode));
                } catch (BookIsNotAvailableException e) {
                    print(e.getMessage());
                }
            }
            else if(menu.get(menuOption).equals(MessagesUtil.RETURN_BOOK_MENU)){
                print(MessagesUtil.CHOOSE_BOOK_RETURN);
                int bookCode = console.nextInt();
                try {
                    print(bibliotecaManager.returnBook(bookCode));
                } catch (InvalidBookException e) {
                    print(e.getMessage());
                }
            }
        }
    }

    public void printAllAvailableBooks(){
        print(MessagesUtil.LIST_ALL_BOOKS_MESSAGE);
        List<Book> availableBooks = bibliotecaManager.getAvailableBooks();
        for(Book book: availableBooks){
            if(book.isAvailable()){
                print(book.getId() + ". " + book.getName());
            }
        }
        print("***********************************");
    }

    private void print(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.startBiblioteca();
    }
}
