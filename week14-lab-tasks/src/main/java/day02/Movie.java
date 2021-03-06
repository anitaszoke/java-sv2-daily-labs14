package day02;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private int length;
    private List<String> actors = new ArrayList<>();

    public Movie(String title, int length, List<String> actors) {
        this.title = title;
        this.length = length;
        this.actors = actors;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public List<String> getActors() {
        return actors;
    }
}