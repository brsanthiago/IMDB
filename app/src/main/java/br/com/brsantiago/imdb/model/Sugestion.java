package br.com.brsantiago.imdb.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 23/07/17.
 */
@Entity(tableName = "sugestion")
public class Sugestion implements Serializable {
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
    @SerializedName("Poster")
    @ColumnInfo(name = "poster")
    private String poster;

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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
