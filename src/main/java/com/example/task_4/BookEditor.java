package com.example.task_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class BookEditor {
    private CollectionBook booklist = CollectionBook.init();
    private Book book;
    @FXML
    private Button cancel;

    @FXML
    private TextField Titlefield;
    @FXML
    private TextField Authorfield;
    @FXML
    private TextField Colvaluefield;
    @FXML
    private TextField Rencostfield;
    @FXML
    private Button ok;

    public Book getBook() {
        return book;
    }
    public void actionClose(ActionEvent actionEvent)
    {
        Node sourse = (Node) actionEvent.getSource();
        Stage stage =(Stage)  sourse.getScene().getWindow();
        stage.hide();
    }
    public void setReader(Book book)
    {
        this.book = book;
        Titlefield.setText(book.title.get());
        Authorfield.setText(book.author.get());
        Colvaluefield.setText(book.colvalue.get());
        Rencostfield.setText(book.rencost.get());


    }
    public void actionSave(ActionEvent actionEvent) throws SQLException {
        if(book.getId()==0)
        {
        book.setId(CollectionBook.size);
        book.setAuthor(Authorfield.getText());
        book.setTitle( Titlefield.getText());
        book.setColvalue(Colvaluefield.getText());
        book.setRencost(Rencostfield.getText());
        CollectionBook.size++;
        booklist.addBook(book);
        DBHandler dbHandler = new DBHandler();
        dbHandler.insertbook(book);
        actionClose(actionEvent);
        }
        book.setAuthor(Titlefield.getText());
        book.setTitle(Authorfield.getText());
        book.setColvalue(Colvaluefield.getText());
        book.setRencost(Rencostfield.getText());
        booklist.updateBook(book);
        actionClose(actionEvent);
    }

}
