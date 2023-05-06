package com.example.task_4;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.SingleSelectionModel;

public class Order
{
    SimpleIntegerProperty id;
    SimpleStringProperty gdata;
    SimpleStringProperty rdata;
    Reader reader;
    Book book;
    public Order(int id, String gdata, String rdata, Reader reader , Book book)
    {
        this.id = new SimpleIntegerProperty(id);
        this.gdata = new SimpleStringProperty (gdata);
        this.rdata =new SimpleStringProperty (rdata);
        this.reader =reader;
        this.book = book;
    }

    public String getRdata() {
        return rdata.get();
    }

    public int getId() {
        return id.get();
    }

    public String getGdata() {
        return gdata.get();
    }

    public int getBook(){
        return book.getId();
    }
    public int getReader()
    {
        return reader.getId();
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setRdata(String rdata) {
        this.rdata.set(rdata);
    }

    public void setGdata(String gdata) {
        this.gdata.set(gdata);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id.get() +
                ", \"gdata\": \"" + gdata.get() + '\"' +
                ", \"rdata\": \"" + rdata.get() + '\"' +
                ", \"Reader\": " + reader.getId() +
                ", \"Book\": " + book.getId() +
                '}';
    }


}


