package com.twu.biblioteca;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.expections.ItemIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidItemException;
import com.twu.biblioteca.expections.InvalidMenuException;
import com.twu.biblioteca.services.BibliotecaManager;
import com.twu.biblioteca.util.MessagesUtil;

import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    public static final int EXIT_OPTION = 4;
    private Scanner console;

    private BibliotecaManager bibliotecaManager;

    public BibliotecaApp(){
        bibliotecaManager = new BibliotecaManager();
    }

    public BibliotecaManager getBibliotecaManager() {
        return bibliotecaManager;
    }

    public void startBiblioteca(){
        String message = bibliotecaManager.loadBibliotecaApplication();
        print(message);

        console = new Scanner(System.in);
        int nextOption = 0;

        while (nextOption != EXIT_OPTION){
            printMenu();
            try {
                nextOption = Integer.parseInt(console.next());
                chooseMenuOption(nextOption);
            } catch (NumberFormatException e){
                print(new InvalidMenuException().getMessage());
            }
            catch (InvalidMenuException e) {
                print(e.getMessage());
            }
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
            try{
                if(menu.get(menuOption).equals(MessagesUtil.LIST_BOOKS_MENU)){
                    printAllAvailableBooks();
                }
                else if(menu.get(menuOption).equals(MessagesUtil.CHECKOUT_BOOK_MENU)){
                    print(MessagesUtil.CHOOSE_BOOK_CHECKOUT);
                    printAllAvailableBooks();
                    int bookCode = Integer.parseInt(console.next());
                    print(bibliotecaManager.checkoutItem(bookCode));

                }
                else if(menu.get(menuOption).equals(MessagesUtil.RETURN_BOOK_MENU)){
                    print(MessagesUtil.CHOOSE_BOOK_RETURN);
                    int bookCode = Integer.parseInt(console.next());
                    print(bibliotecaManager.returnItem(bookCode));

                }
            }
            catch (ItemIsNotAvailableException e) {
                print(e.getMessage());
            }
            catch (InvalidItemException e) {
                print(e.getMessage());
            }
            catch (NumberFormatException e){
                throw new InvalidMenuException();
            }
        }
    }

    private void printAllAvailableBooks(){
        print(MessagesUtil.LIST_ALL_BOOKS_MESSAGE);
        List<Book> availableBooks = bibliotecaManager.getAvailableBooks();
        for(Book book: availableBooks){
            if(book.isAvailable()){
                print(book.getId() + ". " + book.getName());
            }
        }
        print("***********************************");
    }

    private void printMenu(){
        print("");
        print(MessagesUtil.MENU_WELCOME_MESSAGE);
        for(String item: bibliotecaManager.getMenu()){
            print(item);
        }
    }

    private void print(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.startBiblioteca();
    }
}
