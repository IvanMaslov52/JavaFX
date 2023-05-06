package com.example.task_4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CollectionReader {
    private static CollectionReader collectionReader;
    private static ObservableList<Reader> readerList = FXCollections.observableArrayList();
    public static int size;

    private CollectionReader() {
    }
    public static CollectionReader init()
    {
        if(collectionReader == null)
            collectionReader = new CollectionReader();
            return collectionReader;

    }

    public void addReader (Reader reader) throws SQLException {

        readerList.add(reader);

    }

    public void updateReader (Reader reader) throws SQLException {
        DBHandler dbHandler = new DBHandler();
        DBHandler.updatereader(reader);

    }

    public void deleteReader (Reader reader) throws SQLException {
        DBHandler dbHandler = new DBHandler();
        dbHandler.deleteorderfromreader(reader);
        readerList.remove(reader);
        dbHandler.deletereader(reader);
    }

    public ObservableList <Reader> getReaderList()
    {
        return readerList;
    }
    public void fillReaderdata() throws SQLException
    {
        DBHandler dbHandler = new DBHandler();
        if(readerList.size() == 0)
        {
          dbHandler.selectreader();
            if(readerList.size() == 0)
                size =1;
            else
            size = readerList.get(readerList.size()-1).getId()+1;
        }
        else
        {
            readerList.clear();

            dbHandler.selectreader();

        }
    }
}
