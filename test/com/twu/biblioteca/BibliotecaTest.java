package com.twu.biblioteca;


import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.expections.BookIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidBookException;
import com.twu.biblioteca.expections.InvalidMenuException;
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
        BibliotecaApp app = new BibliotecaApp();
        String message = app.loadBibliotecaApplication();
        assertEquals(MessagesUtil.WELCOME_MESSAGE, message);
    }

    @Test
    public void loadAllPreExistingBooksWhenStart(){
        BibliotecaApp app = new BibliotecaApp();
        String message = app.loadBibliotecaApplication();

        List<Book> preExistingBooks = app.getPreExistingBooks();
        List<Book> bookList = app.getAvailableBooks();

        assertEquals(preExistingBooks.size(), bookList.size());
        assertEquals(preExistingBooks.get(0).name, bookList.get(0).name);
        assertEquals(preExistingBooks.get(0).author, bookList.get(0).author);
        assertEquals(preExistingBooks.get(0).year, bookList.get(0).year);
    }

    @Test(expected=InvalidMenuException.class)
    public void selectInvalidOptionMenu() throws InvalidMenuException {
        BibliotecaApp app = new BibliotecaApp();
        app.loadBibliotecaApplication();
        List<String> menu = app.getMenu();
        int option = menu.size() + 1;
        app.chooseMenuOption(option);
    }

    @Test(expected=InvalidMenuException.class)
    public void selectNegativeOptionMenu() throws InvalidMenuException {
        BibliotecaApp app = new BibliotecaApp();
        app.loadBibliotecaApplication();
        List<String> menu = app.getMenu();
        int option = -1;
        app.chooseMenuOption(option);
    }

    @Test
    public void bookCheckoutCannotAppearAtBookList() throws BookIsNotAvailableException {
        BibliotecaApp app = new BibliotecaApp();
        app.loadBibliotecaApplication();
        Book bookToGet = getABookFromExistentList(app);
        String message = app.checkoutBook(bookToGet.id);

        List<Book> books = app.getAvailableBooks();
        for (Book book: books){
            assertNotEquals(bookToGet.name, book.name);
        }
        assertEquals(MessagesUtil.CHECKOUT_MESSAGE, message);
    }

    @Test(expected = BookIsNotAvailableException.class)
    public void bookIsNotAvaliableMessage() throws BookIsNotAvailableException {
        BibliotecaApp app = new BibliotecaApp();
        app.loadBibliotecaApplication();

        Book bookToGet = getABookFromExistentList(app);
        app.checkoutBook(bookToGet.id);

        app.checkoutBook(bookToGet.id);
    }

    @Test(expected = BookIsNotAvailableException.class)
    public void selectNegativeBookId() throws BookIsNotAvailableException {
        BibliotecaApp app = new BibliotecaApp();
        app.loadBibliotecaApplication();
        app.checkoutBook(-1);
    }

    @Test
    public void returnBook() throws BookIsNotAvailableException, InvalidBookException {
        BibliotecaApp app = new BibliotecaApp();
        app.loadBibliotecaApplication();

        Book bookToReturn = getABookFromExistentList(app);
        app.checkoutBook(bookToReturn.id);

        String message = app.returnBook(bookToReturn.id);
        assertEquals(MessagesUtil.RETURN_MESSAGE, message);
    }

    @Test(expected = InvalidBookException.class)
    public void returnAvailableBook() throws InvalidBookException {
        BibliotecaApp app = new BibliotecaApp();
        app.loadBibliotecaApplication();

        Book bookToReturn = getABookFromExistentList(app);
        app.returnBook(bookToReturn.id);
    }

    private Book getABookFromExistentList(BibliotecaApp app) {
        List<Book> preExistingBooks = app.getPreExistingBooks();
        return preExistingBooks.get(1);
    }

}
