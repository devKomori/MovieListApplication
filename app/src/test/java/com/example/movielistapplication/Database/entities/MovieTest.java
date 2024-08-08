package com.example.movielistapplication.Database.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is used to test the Movie entity class for the database.
 */


public class MovieTest {
    Movie movie;

    @Before
    public void setUp() {
        movie = new Movie(1, null, "backdropPath", "originalLanguage", "originalTitle",
                "overview", 5.0, "posterPath", "releaseDate", "title", null, 2.0, 3);
    }

    @After
    public void tearDown() {
        movie = null;
    }

    @Test
    public void getAdult() {
        assertEquals(null, movie.getAdult());
    }

    @Test
    public void setAdult() {
        movie.setAdult(true);
        assertEquals(true, movie.getAdult());
    }

    @Test
    public void getBackdropPath() {
        assertEquals("backdropPath", movie.getBackdropPath());
    }

    @Test
    public void setBackdropPath() {
        movie.setBackdropPath("backdropPathTwo");
        assertEquals("backdropPathTwo", movie.getBackdropPath());
    }

    @Test
    public void getId() {
        assertEquals(Integer.valueOf(1), movie.getId());
    }

    @Test
    public void setId() {
        movie.setId(2);
        assertEquals(Integer.valueOf(2), movie.getId());
    }

    @Test
    public void getOriginalLanguage() {
        assertEquals("originalLanguage", movie.getOriginalLanguage());
    }

    @Test
    public void setOriginalLanguage() {
        movie.setOriginalLanguage("originalLanguageTwo");
        assertEquals("originalLanguageTwo", movie.getOriginalLanguage());
    }

    @Test
    public void getOriginalTitle() {
        assertEquals("originalTitle", movie.getOriginalTitle());
    }

    @Test
    public void setOriginalTitle() {
        movie.setOriginalTitle("originalTitleTwo");
        assertEquals("originalTitleTwo", movie.getOriginalTitle());
    }

    @Test
    public void getOverview() {
        assertEquals("overview", movie.getOverview());
    }

    @Test
    public void setOverview() {
        movie.setOverview("overviewTwo");
        assertEquals("overviewTwo", movie.getOverview());
    }

    @Test
    public void getPopularity() {
        assertEquals(Double.valueOf(5.0), movie.getPopularity());
    }

    @Test
    public void setPopularity() {
        movie.setPopularity(6.0);
        assertEquals(Double.valueOf(6.0), movie.getPopularity());
    }

    @Test
    public void getPosterPath() {
        assertEquals("posterPath", movie.getPosterPath());
    }

    @Test
    public void setPosterPath() {
        movie.setPosterPath("posterPathTwo");
        assertEquals("posterPathTwo", movie.getPosterPath());
    }

    @Test
    public void getReleaseDate() {
        assertEquals("releaseDate", movie.getReleaseDate());
    }

    @Test
    public void setReleaseDate() {
        movie.setReleaseDate("releaseDateTwo");
        assertEquals("releaseDateTwo", movie.getReleaseDate());
    }

    @Test
    public void getTitle() {
        assertEquals("title", movie.getTitle());
    }

    @Test
    public void setTitle() {
        movie.setTitle("titleTwo");
        assertEquals("titleTwo", movie.getTitle());
    }

    @Test
    public void getVoteAverage() {
        assertEquals(Double.valueOf(2.0), movie.getVoteAverage());
    }

    @Test
    public void setVoteAverage() {
        movie.setVoteAverage(3.0);
        assertEquals(Double.valueOf(3.0), movie.getVoteAverage());
    }

    @Test
    public void getVoteCount() {
        assertEquals(Integer.valueOf(3), movie.getVoteCount());
    }

    @Test
    public void setVoteCount() {
        movie.setVoteCount(4);
        assertEquals(Integer.valueOf(4), movie.getVoteCount());
    }







}
