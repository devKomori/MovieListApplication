package com.example.movielistapplication.Database.DAOS;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.movielistapplication.Database.MovieListDatabase;
import com.example.movielistapplication.Database.entities.Movie;
import java.util.Collections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MovieDaoTest {

  private MovieListDatabase movieListDatabase;
  private MovieDao movieDao;

  Movie testMovie = new Movie(1, false, "backdropPath", "originalLanguage", "originalTitle",
      "overview", 5.0, "posterPath", "releaseDate", "title", null, 2.0, 3);
  Movie testMovie2 = new Movie(2, true, "backdropPath2", "originalLanguage2", "originalTitle2",
      "overview2", 10.0, "posterPath2", "releaseDate2", "title2", false, 4.0, 6);

  @Before
  public void setupDataBase() {
    movieListDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieListDatabase.class)
        .allowMainThreadQueries().build();

    movieDao = movieListDatabase.movieDao();
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
  public void movieInsertionAndDeletionTest() {
    assertNull(movieDao.getMovieByMovieIdAsMovie(1));
    movieDao.insert(testMovie);
    assertEquals(testMovie, movieDao.getMovieByMovieIdAsMovie(1));
    movieDao.delete(testMovie);
    assertNull(movieDao.getMovieByTitleAsMovie("title"));
  }

  @Test
  public void movieDeleteAllTest() {
    assertNull(movieDao.getMovieByTitleAsMovie("title"));
    movieDao.insert(testMovie);
    assertEquals(testMovie, movieDao.getMovieByTitleAsMovie("title"));
    movieDao.deleteAll();
    assertNull(movieDao.getMovieByTitleAsMovie("title"));
  }

  @Test
  public void getAllMoviesTest() {
    assertEquals(Collections.emptyList(), movieDao.getAllMoviesAsList());
    movieDao.insert(testMovie);
    movieDao.insert(testMovie2);
    assertEquals(2, movieDao.getAllMoviesAsList().size());
  }

  @Test
  public void getMovieByTitleTest() {
    assertEquals(Collections.emptyList(), movieDao.getAllMoviesAsList());
    movieDao.insert(testMovie);
    assertEquals(testMovie, movieDao.getMovieByTitleAsMovie("title"));
  }

  @Test
  public void getMovieByIdTest() {
    assertEquals(Collections.emptyList(), movieDao.getAllMoviesAsList());
    movieDao.insert(testMovie);
    assertEquals(testMovie, movieDao.getMovieByMovieIdAsMovie(1));
  }
}