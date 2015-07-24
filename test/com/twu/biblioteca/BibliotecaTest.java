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
    public void listAllBooksWhenStart(){
        BibliotecaApp app = new BibliotecaApp();
        Book book1 = new Book("Book 1");
        app.addBookToBiblioteca(book1);

        List<Book> bookList = app.showAllBooks();
        assertEquals("Book 1", bookList.get(0).name);

    }
}
