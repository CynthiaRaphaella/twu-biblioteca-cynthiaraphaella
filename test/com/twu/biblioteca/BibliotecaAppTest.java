package com.twu.biblioteca;


import com.twu.biblioteca.expections.InvalidMenuException;
import org.junit.Test;

import java.util.List;

public class BibliotecaAppTest {

    @Test(expected=InvalidMenuException.class)
    public void selectInvalidOptionMenu() throws InvalidMenuException {
        BibliotecaApp app = new BibliotecaApp();
        app.getBibliotecaManager().loadBibliotecaApplication();
        List<String> menu = app.getBibliotecaManager().getMenu();
        int option = menu.size() + 1;
        app.chooseMenuOption(option);
    }

    @Test(expected=InvalidMenuException.class)
    public void selectNegativeOptionMenu() throws InvalidMenuException {
        BibliotecaApp app = new BibliotecaApp();
        app.getBibliotecaManager().loadBibliotecaApplication();
        int option = -1;
        app.chooseMenuOption(option);
    }

}
