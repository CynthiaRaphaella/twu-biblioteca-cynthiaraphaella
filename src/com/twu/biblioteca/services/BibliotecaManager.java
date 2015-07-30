package com.twu.biblioteca.services;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.data.Item;
import com.twu.biblioteca.data.Movie;
import com.twu.biblioteca.expections.ItemIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidItemException;
import com.twu.biblioteca.util.MessagesUtil;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaManager {

    private List<Item> itens = new ArrayList<Item>();
    private List<String> menu = new ArrayList<String>();

    public String loadBibliotecaApplication(){
        seedMenu();
        seedPreExistingItens(getPreExistingItens());
        return MessagesUtil.WELCOME_MESSAGE;
    }

    public void seedMenu() {
        menu.add(MessagesUtil.LIST_BOOKS_MENU);
        menu.add(MessagesUtil.CHECKOUT_BOOK_MENU);
        menu.add(MessagesUtil.RETURN_BOOK_MENU);
        menu.add(MessagesUtil.LIST_MOVIES_MENU);
        menu.add(MessagesUtil.CHECKOUT_MOVIE_MENU);
        menu.add(MessagesUtil.RETURN_MOVIE_MENU);
        menu.add(MessagesUtil.QUIT_OPTION_MENU);
    }

    public void seedPreExistingItens(List<Item> preExistingItens){
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

}
