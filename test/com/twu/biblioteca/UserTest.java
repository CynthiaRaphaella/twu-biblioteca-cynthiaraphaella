package com.twu.biblioteca;

import com.twu.biblioteca.data.User;
import com.twu.biblioteca.expections.InvalidLibraryNumberException;
import com.twu.biblioteca.services.BibliotecaManager;
import com.twu.biblioteca.util.Seed;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    BibliotecaManager manager;

    @Before
    public void setUp() throws InvalidLibraryNumberException {
        manager = new BibliotecaManager();
        manager.loadMenu();
        manager.loadUsers(Seed.getSeedUsers());
        manager.loadItens(Seed.getSeedItens());
    }

    @Test
    public void createUserCredentials() throws InvalidLibraryNumberException {
        String libraryNumber = "123-4567";
        String password = "admin";
        User user = new User(libraryNumber, password, "", "", "", "");
        manager.getUsers().add(user);
        assertNotNull(manager.getUser(libraryNumber));
    }

    @Test(expected = InvalidLibraryNumberException.class)
    public void createInvalidUser() throws InvalidLibraryNumberException {
        String libraryNumber = "1235-45678";
        String password = "admin";
        new User(libraryNumber, password, "", "", "", "");
    }

    @Test
    public void loginAnExistentUser() throws InvalidLibraryNumberException {
        User user = getAUser();
        assertTrue(manager.login(user.getLibraryNumber(), user.getPassword()));
    }

    @Test
    public void loginAnUnexistentUser() throws InvalidLibraryNumberException {
        User user = getAUser();
        assertFalse(manager.login("000-0000", user.getPassword()));
    }

    @Test
    public void loginAnExistentUserWithWrongPassword() throws InvalidLibraryNumberException {
        assertFalse(manager.login(getAUser().getLibraryNumber(), ""));
    }

    @Test
    public void listInfomationsFromLoggedUser() throws InvalidLibraryNumberException{
        User user = getAUser();
        manager.login(user.getLibraryNumber(), user.getPassword());
        User loggedUser = manager.getLoggedUser();
        assertEquals(user.getLibraryNumber(), loggedUser.getLibraryNumber());
    }

    private User getAUser() throws InvalidLibraryNumberException {
        return Seed.getSeedUsers().get(0);
    }
}
