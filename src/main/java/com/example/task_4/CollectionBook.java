package com.example.task_4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CollectionBook {
    private static CollectionBook collectionBook;
    private static ObservableList<Book> bookList = FXCollections.observableArrayList();
    public static int size;
    private CollectionBook()
    {}
    public static CollectionBook init()
    {
        if(collectionBook == null)
            collectionBook = new CollectionBook();
        return collectionBook;
    }

    public void addBook(Book book)
    {
        bookList.add(book);
    }
    public void updateBook(Book book) throws SQLException {
        DBHandler dbHandler =new DBHandler();
        dbHandler.updatebook(book);

    }

    public void deleteBook(Book book) throws SQLException {
        DBHandler dbHandler  = new DBHandler();
        dbHandler.deleteorderfrombook(book);
        bookList.remove(book);
        dbHandler.deletebook(book);
    }

    public ObservableList<Book> getBookList() {
        return bookList;
    }
    public void fillBookdata() throws SQLException {
        DBHandler dbHandler = new DBHandler();
        if(bookList.size() == 0)
        {
            dbHandler.selectbook();
            if(bookList.size() == 0)
                size =1;
            else
            size = bookList.get(bookList.size()-1).getId()+1;
        }
        else
        {
            bookList.clear();
            dbHandler.selectbook();

        }
    }

}
