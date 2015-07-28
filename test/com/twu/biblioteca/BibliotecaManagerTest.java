package com.twu.biblioteca;

import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.expections.BookIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidBookException;
import com.twu.biblioteca.services.BibliotecaManager;
import com.twu.biblioteca.util.MessagesUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BibliotecaManagerTest {

    BibliotecaManager manager;

    @Before
    public void loadManager(){
        manager = new BibliotecaManager();
    }

    @Test
    public void showWelcomeMessageWhenIStartTheApp() {
        String message = manager.loadBibliotecaApplication();
        assertEquals(MessagesUtil.WELCOME_MESSAGE, message);
    }

    @Test
    public void loadAllPreExistingBooksWhenStart(){
        manager.loadBibliotecaApplication();

        List<Book> preExistingBooks = manager.getPreExistingBooks();
        List<Book> bookList = manager.getAvailableBooks();

        assertEquals(preExistingBooks.size(), bookList.size());
        assertEquals(preExistingBooks.get(0).getName(), bookList.get(0).getName());
        assertEquals(preExistingBooks.get(0).getAuthor(), bookList.get(0).getAuthor());
        assertEquals(preExistingBooks.get(0).getYear(), bookList.get(0).getYear());
    }

    @Test
    public void bookCheckoutCannotAppearAtBookList() throws BookIsNotAvailableException {
        manager.loadBibliotecaApplication();
        Book bookToGet = getABookFromExistentList(manager);
        String message = manager.checkoutBook(bookToGet.getId());

        assertEquals(MessagesUtil.CHECKOUT_MESSAGE, message);

        List<Book> books = manager.getAvailableBooks();
        for (Book book: books){
            assertNotEquals(bookToGet.getName(), book.getName());
        }
    }

    @Test(expected = BookIsNotAvailableException.class)
    public void bookIsNotAvaliableMessage() throws BookIsNotAvailableException {
        manager.loadBibliotecaApplication();

        Book bookToGet = getABookFromExistentList(manager);
        manager.checkoutBook(bookToGet.getId());

        manager.checkoutBook(bookToGet.getId());
    }

    @Test(expected = BookIsNotAvailableException.class)
    public void selectNegativeBookId() throws BookIsNotAvailableException {
        manager.loadBibliotecaApplication();
        manager.checkoutBook(-1);
    }

    @Test
    public void returnBook() throws BookIsNotAvailableException, InvalidBookException {
        manager.loadBibliotecaApplication();

        Book bookToReturn = getABookFromExistentList(manager);
        manager.checkoutBook(bookToReturn.getId());

        String message = manager.returnBook(bookToReturn.getId());
        assertEquals(MessagesUtil.RETURN_MESSAGE, message);
    }

    @Test(expected = InvalidBookException.class)
    public void returnAvailableBook() throws InvalidBookException {
        manager.loadBibliotecaApplication();

        Book bookToReturn = getABookFromExistentList(manager);
        manager.returnBook(bookToReturn.getId());
    }

    private Book getABookFromExistentList(BibliotecaManager manager) {
        List<Book> preExistingBooks = manager.getPreExistingBooks();
        return preExistingBooks.get(1);
    }
}
