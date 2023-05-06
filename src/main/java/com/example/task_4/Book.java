package com.example.task_4;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Book
{
    SimpleIntegerProperty id;
    SimpleStringProperty title;
    SimpleStringProperty author;
    SimpleStringProperty colvalue;
    SimpleStringProperty rencost;

    public Book(int id, String title, String author, String colvalue, String rencost)
    {
        this.id =new SimpleIntegerProperty (id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty (author);
        this.colvalue =new SimpleStringProperty (colvalue);
        this.rencost =new SimpleStringProperty (rencost);
    }

    public int getId(){return id.get();}

    public String getRencost() {
        return rencost.get();
    }

    public String getColvalue() {
        return colvalue.get();
    }

    public String getAuthor() {
        return author.get();
    }

    public String getTitle() {
        return title.get();
    }

    public void setRencost(String rencost) {
        this.rencost.set(rencost);
    }

    public void setColvalue(String colvalue) {
        this.colvalue.set(colvalue);
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id.get() +
                ", \"Title\": \"" + title.get() + '\"' +
                ", \"Author\": \"" + author.get() + '\"' +
                ", \"colvalue\": \"" + colvalue.get() + '\"' +
                ", \"rencost\": \"" + rencost.get() + '\"' +
                '}';
    }

    public void setId(int id) {
        this.id.set(id);
    }
}
