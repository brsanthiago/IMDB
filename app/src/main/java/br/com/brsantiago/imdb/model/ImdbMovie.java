package br.com.brsantiago.imdb.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by bruno on 23/07/17.
 */
@Entity(tableName = "movie")
public class ImdbMovie implements Serializable {
    @SerializedName("imdbID")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    private String imdbId;
    @SerializedName("Title")
    @ColumnInfo(name = "title")
    private String title;
    @SerializedName("Year")
    @ColumnInfo(name = "year")
    private String year;
    @SerializedName("Rated")
    @ColumnInfo(name = "rated")
    private String rated;
    @SerializedName("Released")
    @ColumnInfo(name = "released")
    private String released;
    @SerializedName("Runtime")
    @ColumnInfo(name = "runtime")
    private String runtime;
    @SerializedName("Genre")
    @ColumnInfo(name = "genre")
    private String genre;
    @SerializedName("Director")
    @ColumnInfo(name = "director")
    private String director;
    @SerializedName("Writer")
    @ColumnInfo(name = "writer")
    private String writer;
    @SerializedName("Actors")
    @ColumnInfo(name = "actors")
    private String actors;
    @SerializedName("Plot")
    @ColumnInfo(name = "plot")
    private String plot;
    @SerializedName("Language")
    @ColumnInfo(name = "language")
    private String language;
    @SerializedName("Country")
    @ColumnInfo(name = "country")
    private String country;
    @SerializedName("Awards")
    @ColumnInfo(name = "awards")
    private String awards;
    @SerializedName("Poster")
    @ColumnInfo(name = "poster")
    private String poster;
    @SerializedName("Metascore")
    @ColumnInfo(name = "metascore")
    private String metaScore;
    @SerializedName("imdbRating")
    @ColumnInfo(name = "imdbRating")
    private String imdbRating;
    @SerializedName("imdbVotes")
    @ColumnInfo(name = "imdbVotes")
    private String imdbVotes;
    @SerializedName("Type")
    @ColumnInfo(name = "type")
    private String type;
    @SerializedName("DVD")
    @ColumnInfo(name = "dvd")
    private String dvd;
    @SerializedName("BoxOffice")
    @ColumnInfo(name = "boxOffice")
    private String boxOffice;
    @SerializedName("Production")
    @ColumnInfo(name = "production")
    private String production;
    @SerializedName("Website")
    @ColumnInfo(name = "webSite")
    private String webSite;
    @SerializedName("Response")
    @ColumnInfo(name = "response")
    private boolean response;
    @Expose
    @ColumnInfo(name = "saved")
    private boolean saved;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(String metaScore) {
        this.metaScore = metaScore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDvd() {
        return dvd;
    }

    public void setDvd(String dvd) {
        this.dvd = dvd;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
}
