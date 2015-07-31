package com.twu.biblioteca.services;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.data.Item;
import com.twu.biblioteca.data.Movie;
import com.twu.biblioteca.data.User;
import com.twu.biblioteca.expections.InvalidLibraryNumberException;
import com.twu.biblioteca.expections.ItemIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidItemException;
import com.twu.biblioteca.util.MessagesUtil;
import com.twu.biblioteca.util.Seed;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaManager {

    private List<Item> itens = new ArrayList<Item>();
    private List<User> users = new ArrayList<User>();
    private List<String> menu = new ArrayList<String>();
    private User loggedUser;

    public String loadBibliotecaApplication(){
        try{
            loadMenu();
            loadUsers(Seed.getSeedUsers());
            loadItens(Seed.getSeedItens());
            loggedUser = null;
        }
        catch (InvalidLibraryNumberException e){
            return e.getMessage();
        }
        return MessagesUtil.WELCOME_MESSAGE;
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
        if(user != null && user.getPassword().equals(password)){
            this.loggedUser = user;
            return true;
        }
        return false;
    }

    public User getLoggedUser(){
        return this.loggedUser;
    }

    public void loadMenu(){
        menu = new ArrayList<String>();
        menu = Seed.getSeedMenu();
    }
    public void loadUsers(List<User> newUsers){
        users = new ArrayList<User>();
        users.addAll(newUsers);
    }

    public void loadItens(List<Item> preExistingItens){
        itens = new ArrayList<Item>();
        itens.addAll(preExistingItens);
    }

}
