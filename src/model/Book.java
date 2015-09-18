package model;

import java.io.Serializable;

/**
 * Created by bak on 18/09/2015.
 */
public class Book implements Serializable{
    private int ID;
    private long ISBN;
    private String name;
    private String author;
    private Genre genre;

    public Book(long ISBN, String name, String author, Genre genre) {
        this.ISBN = ISBN;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
