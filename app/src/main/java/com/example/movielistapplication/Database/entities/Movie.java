package com.example.movielistapplication.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.movielistapplication.Database.MovieListDatabase;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;

@Entity(tableName = MovieListDatabase.MOVIE_TABLE)
public class Movie {

    @PrimaryKey
    @SerializedName("id")
    private Integer id;

    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("title")
    private String title;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("vote_count")
    private Integer voteCount;

    public Movie(Integer id, Boolean adult, String backdropPath, String originalLanguage,
                 String originalTitle, String overview, Double popularity, String posterPath,
                 String releaseDate, String title, Boolean video, Double voteAverage,
                 Integer voteCount) {
        this.id = id;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }


    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(adult, movie.adult)
            && Objects.equals(backdropPath, movie.backdropPath) && Objects.equals(
            originalLanguage, movie.originalLanguage) && Objects.equals(originalTitle,
            movie.originalTitle) && Objects.equals(overview, movie.overview)
            && Objects.equals(popularity, movie.popularity) && Objects.equals(
            posterPath, movie.posterPath) && Objects.equals(releaseDate, movie.releaseDate)
            && Objects.equals(title, movie.title) && Objects.equals(video,
            movie.video) && Objects.equals(voteAverage, movie.voteAverage)
            && Objects.equals(voteCount, movie.voteCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adult, backdropPath, originalLanguage, originalTitle, overview,
            popularity, posterPath, releaseDate, title, video, voteAverage, voteCount);
    }
}



