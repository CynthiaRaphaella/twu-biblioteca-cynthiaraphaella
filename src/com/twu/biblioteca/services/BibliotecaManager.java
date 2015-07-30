package com.twu.biblioteca.services;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.data.Item;
import com.twu.biblioteca.data.Movie;
import com.twu.biblioteca.data.User;
import com.twu.biblioteca.expections.InvalidLibraryNumberException;
import com.twu.biblioteca.expections.ItemIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidItemException;
import com.twu.biblioteca.util.MessagesUtil;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaManager {

    private List<Item> itens = new ArrayList<Item>();
    private List<User> users = new ArrayList<User>();
    private List<String> menu = new ArrayList<String>();

    public String loadBibliotecaApplication(){
        try{
            seedMenu();
            seedUsers(getPreExistingUsers());
            seedPreExistingItens(getPreExistingItens());
        }
        catch (InvalidLibraryNumberException e){
            return e.getMessage();
        }

        return MessagesUtil.WELCOME_MESSAGE;
    }

    public void seedMenu() {
        menu = new ArrayList<String>();
        menu.add(MessagesUtil.LIST_BOOKS_MENU);
        menu.add(MessagesUtil.CHECKOUT_BOOK_MENU);
        menu.add(MessagesUtil.RETURN_BOOK_MENU);
        menu.add(MessagesUtil.LIST_MOVIES_MENU);
        menu.add(MessagesUtil.CHECKOUT_MOVIE_MENU);
        menu.add(MessagesUtil.RETURN_MOVIE_MENU);
        menu.add(MessagesUtil.QUIT_OPTION_MENU);
    }

    public void seedUsers(List<User> newUsers){
        users = new ArrayList<User>();
        users.addAll(newUsers);
    }

    public void seedPreExistingItens(List<Item> preExistingItens){
        itens = new ArrayList<Item>();
        itens.addAll(preExistingItens);
    }

    public String checkoutItem(int id) throws ItemIsNotAvailableException {
        if(id < 1){
            throw new ItemIsNotAvailableException();
        }
        for(Item item: itens){
            if(item.getId() == id && item.isAvailable()){
                item.checkoutItem();
                return item.getCheckoutMessage();
            }
        }
        throw new ItemIsNotAvailableException();
    }

    public String returnItem(int id) throws InvalidItemException {
        for(Item item: itens){
            if(item.getId() == id && !item.isAvailable()){
                item.returnItem();
                return item.getReturnMessage();
            }
        }
        throw new InvalidItemException();
    }

    public List<Book> getAvailableBooks(){
        return (List<Book>)(List<?>) getAvailableItens(Book.class);
    }

    public List<Movie> getAvailableMovies(){
        return (List<Movie>)(List<?>) getAvailableItens(Movie.class);
    }

    public List<Item> getAvailableItens(Class itemClass){
        List<Item> availableItens = new ArrayList<Item>();
        for(Item item: itens){
            if(item.getType().equals(itemClass) && item.isAvailable()){
                availableItens.add(item);
            }
        }
        return availableItens;
    }

    public List<String> getMenu(){
        return menu;
    }

    public List<User> getUsers(){
        return users;
    }

    public User getUser(String libraryNumber){
        for(User actualUser: users){
            if(actualUser.getLibraryNumber().equals(libraryNumber))
                return actualUser;
        }
        return null;
    }

    public boolean login(String libraryNumber, String password){
        User user = getUser(libraryNumber);
        return user != null && user.getPassword().equals(password);
    }

    private List<Item> getPreExistingItens(){
        List<Item> preExistingItens = new ArrayList<Item>();
        preExistingItens.add(new Book(1, "Book 1", "Author", "1990"));
        preExistingItens.add(new Book(2, "Book 2", "Author", "1996"));
        preExistingItens.add(new Book(3, "Book 3", "Author", "2000"));

        preExistingItens.add(new Movie(4, "Movie 1", "Author", "1990", "1"));
        preExistingItens.add(new Movie(5, "Movie 2", "Author", "1996", "1"));
        preExistingItens.add(new Movie(6, "Movie 3", "Author", "2000", "1"));
        return preExistingItens;
    }

    private List<User> getPreExistingUsers() throws InvalidLibraryNumberException {
        List<User> users = new ArrayList<User>();
        users.add(new User("999-8888", "pass"));
        users.add(new User("222-5678", "1234"));
        return users;
    }

}
