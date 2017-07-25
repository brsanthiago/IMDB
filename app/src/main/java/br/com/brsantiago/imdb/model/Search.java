package br.com.brsantiago.imdb.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 23/07/17.
 */
public class Search implements Serializable {
    @SerializedName("Search")
    private List<Sugestion> sugestions;
    @SerializedName("Response")
    private boolean response;

    public List<Sugestion> getSugestions() {
        return sugestions;
    }

    public void setSugestions(List<Sugestion> sugestions) {
        this.sugestions = sugestions;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
