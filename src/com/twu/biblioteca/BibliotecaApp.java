package com.twu.biblioteca;

import com.twu.biblioteca.data.*;
import com.twu.biblioteca.expections.ItemIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidItemException;
import com.twu.biblioteca.expections.InvalidMenuException;
import com.twu.biblioteca.services.BibliotecaManager;
import com.twu.biblioteca.util.MessagesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private Scanner console;

    private BibliotecaManager bibliotecaManager;

    public BibliotecaApp(){
        bibliotecaManager = new BibliotecaManager();
    }

    public BibliotecaManager getBibliotecaManager() {
        return bibliotecaManager;
    }

    public void startBiblioteca(){
        console = new Scanner(System.in);

        String message = bibliotecaManager.loadBibliotecaApplication();
        print(message);

        while (true){
            if(login()){
                int nextOption = 0;
                int exitOption = bibliotecaManager.getMenu().size();

                while (nextOption != exitOption){
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
                print("Bye!");
            }
            else{
                print(MessagesUtil.CREDENTIALS_PROBLEM_MESSAGE);
            }
        }
    }

    private boolean login() {
        print(MessagesUtil.LOGIN_MESSAGE);
        print("Library Number:");
        String libraryNumber = console.next();
        print("Password");
        String password = console.next();
        return bibliotecaManager.login(libraryNumber, password);
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
                else if(menu.get(menuOption).equals(MessagesUtil.LIST_MOVIES_MENU)){
                    printAllAvailableMovies();
                }
                else if(menu.get(menuOption).equals(MessagesUtil.CHECKOUT_BOOK_MENU)){
                    bookCheckout();
                }
                else if(menu.get(menuOption).equals(MessagesUtil.RETURN_BOOK_MENU)){
                    bookReturn();
                }
                else if(menu.get(menuOption).equals(MessagesUtil.CHECKOUT_MOVIE_MENU)){
                    movieCheckout();
                }
                else if(menu.get(menuOption).equals(MessagesUtil.RETURN_MOVIE_MENU)){
                    movieReturn();
                }
                else if(menu.get(menuOption).equals(MessagesUtil.USER_INFORMATION_MENU)){
                    printUserInformation();
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

    private void bookReturn() throws InvalidItemException {
        print(MessagesUtil.CHOOSE_BOOK_RETURN);
        int bookCode = Integer.parseInt(console.next());
        print(bibliotecaManager.returnItem(bookCode));
    }

    private void movieReturn() throws InvalidItemException {
        print(MessagesUtil.CHOOSE_MOVIE_RETURN);
        int movieCode = Integer.parseInt(console.next());
        print(bibliotecaManager.returnItem(movieCode));
    }

    private void bookCheckout() throws ItemIsNotAvailableException {
        print(MessagesUtil.CHOOSE_BOOK_CHECKOUT);
        printAllAvailableBooks();
        int bookCode = Integer.parseInt(console.next());
        print(bibliotecaManager.checkoutItem(bookCode));
    }

    private void movieCheckout() throws ItemIsNotAvailableException {
        print(MessagesUtil.CHOOSE_MOVIE_CHECKOUT);
        printAllAvailableMovies();
        int movieCode = Integer.parseInt(console.next());
        print(bibliotecaManager.checkoutItem(movieCode));
    }

    private void printUserInformation(){
        User loggedUser = bibliotecaManager.getLoggedUser();
        if(loggedUser != null){
            List<User> listUser = new ArrayList<User>();
            listUser.add(loggedUser);
            printInformation((List<ItemList>) (List<?>) listUser);
        }
    }

    private void printAllAvailableBooks(){
        print(MessagesUtil.LIST_ALL_BOOKS_MESSAGE);
        List<Book> availableBooks = bibliotecaManager.getAvailableBooks();
        printInformation((List<ItemList>) (List<?>) availableBooks);
    }

    private void printAllAvailableMovies(){
        print(MessagesUtil.LIST_ALL_MOVIES_MESSAGE);
        List<Movie> availableMovies = bibliotecaManager.getAvailableMovies();
        printInformation((List<ItemList>)(List<?>)availableMovies);
    }

    private void printInformation(List<ItemList> itens){
        printAsteristicsFullLine();
        if(itens != null && itens.size() > 0){
            print(itens.get(0).getPropertiesNames());
            printAsteristicsFullLine();
            for(ItemList item: itens){
                print(item.getPropertiesValues());
            }
        }
        printAsteristicsFullLine();
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

    private void printAsteristicsFullLine(){
        print("*******************************************************");
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.startBiblioteca();
    }
}
