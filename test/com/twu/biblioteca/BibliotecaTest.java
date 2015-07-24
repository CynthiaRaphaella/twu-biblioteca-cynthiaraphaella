package com.twu.biblioteca;


import org.junit.Test;
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
}
