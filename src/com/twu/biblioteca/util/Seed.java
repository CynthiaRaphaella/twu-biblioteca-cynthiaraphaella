package com.twu.biblioteca.util;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.data.Item;
import com.twu.biblioteca.data.Movie;
import com.twu.biblioteca.data.User;
import com.twu.biblioteca.expections.InvalidLibraryNumberException;

import java.util.ArrayList;
import java.util.List;

public class Seed {

    public static List<Item> getSeedItens(){
        List<Item> preExistingItens = new ArrayList<Item>();
        preExistingItens.addAll(getSeedBooks());
        preExistingItens.addAll(getSeedMovies());
        return preExistingItens;
    }

    public static List<Book> getSeedBooks(){
        List<Book> preExistingBooks = new ArrayList<Book>();
        preExistingBooks.add(new Book(1, "Book 1", "Author", "1990"));
        preExistingBooks.add(new Book(2, "Book 2", "Author", "1996"));
        preExistingBooks.add(new Book(3, "Book 3", "Author", "2000"));
        return preExistingBooks;
    }

    public static List<Movie> getSeedMovies(){
        List<Movie> preExistingMovies = new ArrayList<Movie>();
        preExistingMovies.add(new Movie(4, "Movie 1", "Author", "1990", "1"));
        preExistingMovies.add(new Movie(5, "Movie 2", "Author", "1996", "1"));
        preExistingMovies.add(new Movie(6, "Movie 3", "Author", "2000", "1"));
        return preExistingMovies;
    }

    public static List<User> getSeedUsers() throws InvalidLibraryNumberException {
        List<User> users = new ArrayList<User>();
        users.add(new User("111-1111", "1"));
        users.add(new User("222-5678", "1234"));
        return users;
    }

    public static List<String> getSeedMenu() {
        List<String> menu = new ArrayList<String>();
        menu.add(MessagesUtil.LIST_BOOKS_MENU);
        menu.add(MessagesUtil.CHECKOUT_BOOK_MENU);
        menu.add(MessagesUtil.RETURN_BOOK_MENU);
        menu.add(MessagesUtil.LIST_MOVIES_MENU);
        menu.add(MessagesUtil.CHECKOUT_MOVIE_MENU);
        menu.add(MessagesUtil.RETURN_MOVIE_MENU);
        menu.add(MessagesUtil.QUIT_OPTION_MENU);
        return menu;
    }
}
