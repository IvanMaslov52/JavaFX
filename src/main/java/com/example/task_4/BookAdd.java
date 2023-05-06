package com.example.task_4;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

public class BookAdd {
    private CollectionBook bookList = CollectionBook.init();
   private Book book;
    @FXML
    private TableView tableBook;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    @FXML
    private TableColumn<Book, Integer> columnID;
    @FXML
    private TableColumn<Book, SimpleStringProperty> columnTitle;
    @FXML
    private TableColumn<Book, SimpleStringProperty> columnAuthor;
    @FXML
    private TableColumn<Book,SimpleStringProperty> columnColvalue;
    @FXML
    private TableColumn<Book, SimpleStringProperty> columnRencost;
    public void actionClose(ActionEvent actionEvent)
    {
        Node sourse = (Node) actionEvent.getSource();
        Stage stage =(Stage)  sourse.getScene().getWindow();
        stage.hide();
    }
    @FXML
    public void actionSave(ActionEvent actionEvent) throws SQLException {
        book = (Book) tableBook.getSelectionModel().getSelectedItem();
        actionClose(actionEvent);

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) throws SQLException {
        this.book = book;
        columnID.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<Book, SimpleStringProperty>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<Book, SimpleStringProperty>("author"));
        columnColvalue.setCellValueFactory(new PropertyValueFactory<Book,SimpleStringProperty>("colvalue"));
        columnRencost.setCellValueFactory(new PropertyValueFactory<Book, SimpleStringProperty>("rencost"));
        bookList.fillBookdata();
        tableBook.setItems(bookList.getBookList());

    }
}
