package com.example.movielistapplication.Database.DAOS;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.example.movielistapplication.Database.MovieListDatabase;
import com.example.movielistapplication.Database.entities.User;
import java.util.Collections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UserDaoTest {

  private MovieListDatabase movieListDatabase;
  private UserDao userDao;

  User testUser = new User(1, "user1", "password1");
  User testUser2 = new User(2, "user2", "password2");

  @Before
  public void setupDataBase() {
    movieListDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieListDatabase.class)
        .allowMainThreadQueries().build();

    userDao = movieListDatabase.userDao();
  }

  @After
  public void closeDatabase() {
    movieListDatabase.close();
  }

  @Test
  public void useAppContext() {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    assertEquals("com.example.movielistapplication", appContext.getPackageName());
  }

  @Test
  public void userInsertionAndDeletionTest() {
    userDao.deleteAll();
    assertNull(userDao.getUserByUserIdAsUser(1));
    userDao.insert(testUser);
    assertEquals(testUser, userDao.getUserByUserNameAsUser("user1"));
    userDao.delete(testUser);
    assertNull(userDao.getUserByUserNameAsUser("user1"));
  }

  @Test
  public void userDeletionTest() {

  }

  @Test
  public void userDeleteAllTest() {
    assertNull(userDao.getUserByUserNameAsUser("user1"));
    userDao.insert(testUser);
    assertEquals(testUser, userDao.getUserByUserNameAsUser("user1"));
    userDao.deleteAll();
    assertNull(userDao.getUserByUserNameAsUser("user1"));
  }

  @Test
  public void getAllUsersTest() {
    assertEquals(Collections.emptyList(), userDao.getAllUsersAsList());
    userDao.insert(testUser);
    userDao.insert(testUser2);
    assertEquals(2, userDao.getAllUsersAsList().size());
  }

  @Test
  public void getUserByUserNameTest() {
    assertEquals(Collections.emptyList(), userDao.getAllUsersAsList());
    userDao.insert(testUser);
    assertEquals(testUser, userDao.getUserByUserNameAsUser("user1"));
  }

  @Test
  public void getUserByUserIdTest() {
    assertEquals(Collections.emptyList(), userDao.getAllUsersAsList());
    userDao.insert(testUser);
    assertEquals(testUser, userDao.getUserByUserIdAsUser(1));
  }

  @Test
  public void userUpdateTest() {
    assertEquals(Collections.emptyList(), userDao.getAllUsersAsList());
    userDao.insert(testUser);
    testUser.setUsername("user3");
    userDao.update(testUser);
    assertEquals(testUser, userDao.getUserByUserNameAsUser("user3"));
  }
}