package com.twu.biblioteca;


import com.twu.biblioteca.data.Book;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
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
        List<Book> bookList = app.showAllBooks();

        assertEquals(preExistingBooks.size(), bookList.size());
        assertEquals(preExistingBooks.get(0).name, bookList.get(0).name);
        assertEquals(preExistingBooks.get(0).author, bookList.get(0).author);
        assertEquals(preExistingBooks.get(0).year, bookList.get(0).year);
    }
}
