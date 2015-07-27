package com.twu.biblioteca;


import com.twu.biblioteca.data.Book;
import com.twu.biblioteca.expections.BookIsNotAvailableException;
import com.twu.biblioteca.expections.InvalidMenuException;
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
        String message = app.startBibliotecaApplication();
        assertNotNull(message);
    }

    @Test
    public void loadAllPreExistingBooksWhenStart(){
        BibliotecaApp app = new BibliotecaApp();
        String message = app.startBibliotecaApplication();

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
        app.startBibliotecaApplication();
        List<String> menu = app.getMenu();
        int option = menu.size() + 1;
        app.chooseMenuOption(option);
    }

    @Test(expected=InvalidMenuException.class)
    public void selectNegativeOptionMenu() throws InvalidMenuException {
        BibliotecaApp app = new BibliotecaApp();
        app.startBibliotecaApplication();
        List<String> menu = app.getMenu();
        int option = -1;
        app.chooseMenuOption(option);
    }

    @Test
    public void bookCheckoutCannotAppearAtBookList() throws BookIsNotAvailableException {
        BibliotecaApp app = new BibliotecaApp();
        app.startBibliotecaApplication();
        List<Book> preExistingBooks = app.getPreExistingBooks();
        Book bookToGet = preExistingBooks.get(1);
        String message = app.checkoutBook(bookToGet.id);

        List<Book> books = app.getAvailableBooks();
        for (Book book: books){
            assertNotEquals(bookToGet.name, book.name);
        }
        assertNotNull(message);
    }

    @Test(expected = BookIsNotAvailableException.class)
    public void bookIsNotAvaliableMessage() throws BookIsNotAvailableException {
        BibliotecaApp app = new BibliotecaApp();
        app.startBibliotecaApplication();

        List<Book> preExistingBooks = app.getPreExistingBooks();
        Book bookToGet = preExistingBooks.get(1);
        app.checkoutBook(bookToGet.id);

        app.checkoutBook(bookToGet.id);
    }

    @Test(expected = BookIsNotAvailableException.class)
    public void selectNegativeBookId() throws BookIsNotAvailableException {
        BibliotecaApp app = new BibliotecaApp();
        app.startBibliotecaApplication();
        app.checkoutBook(-1);
    }

    public void returnBook() throws BookIsNotAvailableException {
        BibliotecaApp app = new BibliotecaApp();
        app.startBibliotecaApplication();

        List<Book> preExistingBooks = app.getPreExistingBooks();
        Book bookToGet = preExistingBooks.get(1);
        app.checkoutBook(bookToGet.id);

        String message = app.returnBook(bookToGet.id);
        assertNotNull(message);
    }

}
