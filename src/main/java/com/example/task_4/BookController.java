package com.example.task_4;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class BookController
{
    private CollectionBook bookList = CollectionBook.init();
    @FXML
    private TableView tableBook;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button change;
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

    private Stage bookStage;

    private Parent fxmlEdit;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private BookEditor bookEditor;

    private Stage editBookstage;

    @FXML
    private void initialize() throws SQLException {
        columnID.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<Book, SimpleStringProperty>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<Book, SimpleStringProperty>("author"));
        columnColvalue.setCellValueFactory(new PropertyValueFactory<Book,SimpleStringProperty>("colvalue"));
        columnRencost.setCellValueFactory(new PropertyValueFactory<Book, SimpleStringProperty>("rencost"));
        bookList.fillBookdata();
        tableBook.setItems(bookList.getBookList());
        try {
            fxmlLoader.setLocation(getClass().getResource("book-new.fxml"));
            fxmlEdit = fxmlLoader.load();
            bookEditor = fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void showDialog(ActionEvent actionEvent) throws SQLException {
        Object source = actionEvent.getSource();

        if(!(source instanceof Button))
        {
            return;
        }
        Button clickedButton = (Button) source;
        switch (clickedButton.getId())
        {
            case "add":
                bookEditor.setReader(new Book(0,"","","",""));
                action();
                break;
            case "change":
                bookEditor.setReader((Book) tableBook.getSelectionModel().getSelectedItem());
                action();
                break;
            case "delete":
                bookList.deleteBook((Book) tableBook.getSelectionModel().getSelectedItem());
                break;
            default:

        }
    }
    private void action()
    {
        if(editBookstage == null)
        {
            editBookstage = new Stage();
            editBookstage.setTitle("Change");
            editBookstage.setScene(new Scene(fxmlEdit));
            editBookstage.initModality(Modality.WINDOW_MODAL);
            editBookstage.initOwner(bookStage);
        }
        editBookstage.showAndWait();
    }

    public void setBookStage(Stage bookStage) {
        this.bookStage = bookStage;
    }
}
