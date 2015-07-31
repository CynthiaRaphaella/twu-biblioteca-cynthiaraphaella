package com.twu.biblioteca;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.data.Item;
import com.twu.biblioteca.data.Movie;
import com.twu.biblioteca.expections.InvalidLibraryNumberException;
import com.twu.biblioteca.expections.ItemIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidItemException;
import com.twu.biblioteca.services.BibliotecaManager;
import com.twu.biblioteca.util.MessagesUtil;
import com.twu.biblioteca.util.Seed;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BibliotecaManagerTest {

    BibliotecaManager manager;

    @Before
    public void setUp() throws InvalidLibraryNumberException {
        manager = new BibliotecaManager();
        manager.loadMenu();
        manager.loadUsers(Seed.getSeedUsers());
        manager.loadItens(Seed.getSeedItens());
    }

    @Test
    public void showWelcomeMessageWhenIStartTheApp() {
        String message = manager.loadBibliotecaApplication();
        assertEquals(MessagesUtil.WELCOME_MESSAGE, message);
    }

    @Test
    public void listAllAvailableBooks(){
        List<Book> preExistingBooks = Seed.getSeedBooks();
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
        List<Movie> allPreExistingMovies = Seed.getSeedMovies();
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
        List<Book> allPreExitingBooks = Seed.getSeedBooks();

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
    public void checkoutBook() throws ItemIsNotAvailableException {
        assertEquals(MessagesUtil.CHECKOUT_BOOK_MESSAGE, manager.checkoutItem(getABook().getId()));
    }

    @Test
    public void checkoutMovie() throws ItemIsNotAvailableException {
        assertEquals(MessagesUtil.CHECKOUT_MOVIE_MESSAGE, manager.checkoutItem(getAMovie().getId()));
    }

    @Test
    public void returnBook() throws ItemIsNotAvailableException, InvalidItemException {
        assertReturnItem(getABook(), MessagesUtil.RETURN_BOOK_MESSAGE);
    }

    @Test
    public void returnMovie() throws ItemIsNotAvailableException, InvalidItemException {
        assertReturnItem(getAMovie(), MessagesUtil.RETURN_MOVIE_MESSAGE);
    }

    @Test(expected = InvalidItemException.class)
    public void returnAvailableBook() throws InvalidItemException {
        Item itemToReturn = getABook();
        manager.returnItem(itemToReturn.getId());
    }

    private void assertReturnItem(Item itemToReturn, String expectedReturnMessage) throws ItemIsNotAvailableException, InvalidItemException {
        manager.checkoutItem(itemToReturn.getId());

        String message = manager.returnItem(itemToReturn.getId());
        assertEquals(expectedReturnMessage, message);
    }

    private Book getABook() {
        return Seed.getSeedBooks().get(0);
    }

    private Movie getAMovie() {
        return Seed.getSeedMovies().get(0);
    }

}
