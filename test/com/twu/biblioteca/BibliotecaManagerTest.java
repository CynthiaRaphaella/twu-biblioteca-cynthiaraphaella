package com.twu.biblioteca;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.data.Item;
import com.twu.biblioteca.data.Movie;
import com.twu.biblioteca.data.User;
import com.twu.biblioteca.expections.InvalidLibraryNumberException;
import com.twu.biblioteca.expections.ItemIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidItemException;
import com.twu.biblioteca.services.BibliotecaManager;
import com.twu.biblioteca.util.MessagesUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class BibliotecaManagerTest {

    BibliotecaManager manager;

    @Before
    public void setUp(){
        manager = new BibliotecaManager();
        manager.seedMenu();
        manager.seedPreExistingItens(getPreExistingItens());
    }

    @Test
    public void showWelcomeMessageWhenIStartTheApp() {
        String message = manager.loadBibliotecaApplication();
        assertEquals(MessagesUtil.WELCOME_MESSAGE, message);
    }

    @Test
    public void loadAllPreExistingBooksWhenStart(){
        List<Book> preExistingBooks = getPreExistingBooks();
        List<Book> bookList = manager.getAvailableBooks();

        assertEquals(preExistingBooks.size(), bookList.size());

        for (int i = 0; i < preExistingBooks.size(); i++) {
            assertEquals(preExistingBooks.get(i).getName(), bookList.get(i).getName());
            assertEquals(preExistingBooks.get(i).getAuthor(), bookList.get(i).getAuthor());
            assertEquals(preExistingBooks.get(i).getYear(), bookList.get(i).getYear());
        }
    }

    @Test
    public void listAllAvailableMovies(){
        List<Movie> allPreExistingMovies = getPreExistingMovies();
        List<Movie> allAvailableMovies = manager.getAvailableMovies();

        assertEquals(allPreExistingMovies.size(), allAvailableMovies.size());

        for (int i = 0; i < allPreExistingMovies.size(); i++) {
            assertEquals(allPreExistingMovies.get(i).getName(), allAvailableMovies.get(i).getName());
            assertEquals(allPreExistingMovies.get(i).getYear(), allAvailableMovies.get(i).getYear());
            assertEquals(allPreExistingMovies.get(i).getDirector(), allAvailableMovies.get(i).getDirector());
            assertEquals(allPreExistingMovies.get(i).getRate(), allAvailableMovies.get(i).getRate());
        }
    }

    @Test
    public void showNoneBookWhenAllWereCheckout() throws ItemIsNotAvailableException {
        List<Book> allPreExitingBooks = getPreExistingBooks();

        for (Book book: allPreExitingBooks){
            manager.checkoutItem(book.getId());
        }

        List<Book> availableBook = manager.getAvailableBooks();
        assertEquals(0, availableBook.size());
    }

    @Test
    public void bookCheckoutCannotAppearAtBookList() throws ItemIsNotAvailableException {
        Book bookToGet = getABook();
        String message = manager.checkoutItem(bookToGet.getId());

        assertEquals(MessagesUtil.CHECKOUT_BOOK_MESSAGE, message);

        List<Book> books = manager.getAvailableBooks();
        for (Book book: books){
            assertNotEquals(bookToGet.getName(), book.getName());
        }
    }

    @Test(expected = ItemIsNotAvailableException.class)
    public void itemsNotAvaliableToCheckout() throws ItemIsNotAvailableException {
        Item itemToGet = getABook();
        manager.checkoutItem(itemToGet.getId());

        manager.checkoutItem(itemToGet.getId());
    }

    @Test(expected = ItemIsNotAvailableException.class)
    public void selectNegativeItemId() throws ItemIsNotAvailableException {
        manager.checkoutItem(-1);
    }

    @Test
    public void returnBook() throws ItemIsNotAvailableException, InvalidItemException {
        returnItem(getABook(), MessagesUtil.RETURN_BOOK_MESSAGE);
    }

    @Test
    public void returnMovie() throws ItemIsNotAvailableException, InvalidItemException {
        returnItem(getAMovie(), MessagesUtil.RETURN_MOVIE_MESSAGE);
    }

    @Test(expected = InvalidItemException.class)
    public void returnAvailableBook() throws InvalidItemException {
        Item itemToReturn = getABook();
        manager.returnItem(itemToReturn.getId());
    }

    @Test
    public void createUserCredentials() throws InvalidLibraryNumberException {
        String libraryNumber = "123-4567";
        String password = "admin";
        User user = new User(libraryNumber, password);
        manager.getUsers().add(user);
        assertNotNull(manager.getUser(libraryNumber));
    }

    @Test(expected = InvalidLibraryNumberException.class)
    public void createInvalidUser() throws InvalidLibraryNumberException {
        String libraryNumber = "1235-45678";
        String password = "admin";
        User user = new User(libraryNumber, password);
    }

    private void returnItem(Item itemToReturn, String expectedReturnMessage) throws ItemIsNotAvailableException, InvalidItemException {
        manager.checkoutItem(itemToReturn.getId());

        String message = manager.returnItem(itemToReturn.getId());
        assertEquals(expectedReturnMessage, message);
    }

    public List<Item> getPreExistingItens(){
        List<Item> preExistingItens = new ArrayList<Item>();
        preExistingItens.addAll(getPreExistingBooks());
        preExistingItens.addAll(getPreExistingMovies());
        return preExistingItens;
    }

    private List<Book> getPreExistingBooks(){
        List<Book> books = new ArrayList<Book>();
        books.add(new Book(1, "Book 1", "Author", "1990"));
        books.add(new Book(2, "Book 2", "Author", "1980"));
        return books;
    }

    private List<Movie> getPreExistingMovies(){
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(3, "Movie 1", "Author", "1990", "1"));
        movies.add(new Movie(4, "Movie 2", "Author", "1996", "1"));
        return movies;
    }

    private Book getABook() {
        return getPreExistingBooks().get(0);
    }

    private Movie getAMovie() {
        return getPreExistingMovies().get(0);
    }

}
