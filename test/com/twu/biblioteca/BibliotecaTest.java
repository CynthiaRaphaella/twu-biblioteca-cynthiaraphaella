package com.twu.biblioteca;


import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.expections.BookIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidBookException;
import com.twu.biblioteca.expections.InvalidMenuException;
import com.twu.biblioteca.services.BibliotecaManager;
import com.twu.biblioteca.util.MessagesUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class BibliotecaTest {

    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void showWelcomeMessageWhenIStartTheApp() {
        BibliotecaManager app = new BibliotecaManager();
        String message = app.loadBibliotecaApplication();
        assertEquals(MessagesUtil.WELCOME_MESSAGE, message);
    }

    @Test
    public void loadAllPreExistingBooksWhenStart(){
        BibliotecaManager app = new BibliotecaManager();
        String message = app.loadBibliotecaApplication();

        List<Book> preExistingBooks = app.getPreExistingBooks();
        List<Book> bookList = app.getAvailableBooks();

        assertEquals(preExistingBooks.size(), bookList.size());
        assertEquals(preExistingBooks.get(0).getName(), bookList.get(0).getName());
        assertEquals(preExistingBooks.get(0).getAuthor(), bookList.get(0).getAuthor());
        assertEquals(preExistingBooks.get(0).getYear(), bookList.get(0).getYear());
    }

    @Test(expected=InvalidMenuException.class)
    public void selectInvalidOptionMenu() throws InvalidMenuException {
        BibliotecaManager manager = new BibliotecaManager();
        BibliotecaApp app = new BibliotecaApp(manager);
        manager.loadBibliotecaApplication();
        List<String> menu = manager.getMenu();
        int option = menu.size() + 1;
        app.chooseMenuOption(option);
    }

    @Test(expected=InvalidMenuException.class)
    public void selectNegativeOptionMenu() throws InvalidMenuException {
        BibliotecaManager manager = new BibliotecaManager();
        BibliotecaApp app = new BibliotecaApp(manager);
        manager.loadBibliotecaApplication();
        List<String> menu = manager.getMenu();
        int option = -1;
        app.chooseMenuOption(option);
    }

    @Test
    public void bookCheckoutCannotAppearAtBookList() throws BookIsNotAvailableException {
        BibliotecaManager app = new BibliotecaManager();
        app.loadBibliotecaApplication();
        Book bookToGet = getABookFromExistentList(app);
        String message = app.checkoutBook(bookToGet.getId());

        List<Book> books = app.getAvailableBooks();
        for (Book book: books){
            assertNotEquals(bookToGet.getName(), book.getName());
        }
        assertEquals(MessagesUtil.CHECKOUT_MESSAGE, message);
    }

    @Test(expected = BookIsNotAvailableException.class)
    public void bookIsNotAvaliableMessage() throws BookIsNotAvailableException {
        BibliotecaManager app = new BibliotecaManager();
        app.loadBibliotecaApplication();

        Book bookToGet = getABookFromExistentList(app);
        app.checkoutBook(bookToGet.getId());

        app.checkoutBook(bookToGet.getId());
    }

    @Test(expected = BookIsNotAvailableException.class)
    public void selectNegativeBookId() throws BookIsNotAvailableException {
        BibliotecaManager app = new BibliotecaManager();
        app.loadBibliotecaApplication();
        app.checkoutBook(-1);
    }

    @Test
    public void returnBook() throws BookIsNotAvailableException, InvalidBookException {
        BibliotecaManager app = new BibliotecaManager();
        app.loadBibliotecaApplication();

        Book bookToReturn = getABookFromExistentList(app);
        app.checkoutBook(bookToReturn.getId());

        String message = app.returnBook(bookToReturn.getId());
        assertEquals(MessagesUtil.RETURN_MESSAGE, message);
    }

    @Test(expected = InvalidBookException.class)
    public void returnAvailableBook() throws InvalidBookException {
        BibliotecaManager app = new BibliotecaManager();
        app.loadBibliotecaApplication();

        Book bookToReturn = getABookFromExistentList(app);
        app.returnBook(bookToReturn.getId());
    }

    private Book getABookFromExistentList(BibliotecaManager app) {
        List<Book> preExistingBooks = app.getPreExistingBooks();
        return preExistingBooks.get(1);
    }

}
