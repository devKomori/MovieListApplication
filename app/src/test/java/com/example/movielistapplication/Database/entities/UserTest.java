package com.example.movielistapplication.Database.entities;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This class is used to test the User entity class for the database.
 */
public class UserTest {
    User user;

    @Before
    public void setUp() {
        user = new User("testUser", "testPassword");
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void getUserId() {
        assertEquals(0, user.getUserId());
    }

    @Test
    public void setUserId() {
        user.setUserId(1);
        assertEquals(1, user.getUserId());
    }

    @Test
    public void getUsername() {
        assertEquals("testUser", user.getUsername());
    }

    @Test
    public void setUsername() {
        user.setUsername("newUser");
        assertEquals("newUser", user.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    public void setPassword() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void isAdmin() {
        assertEquals(false, user.isAdmin());
    }

    @Test
    public void setAdmin() {
        user.setAdmin(true);
        assertEquals(true, user.isAdmin());
    }




}
