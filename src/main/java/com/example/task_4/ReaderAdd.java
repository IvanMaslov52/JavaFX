package com.example.task_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ReaderAdd {
    private Reader reader;
    private CollectionReader readerList = CollectionReader.init();
    @FXML
    private TableView tableReader;
    @FXML
    private TableColumn<Reader, Integer> columnID;
    @FXML
    private TableColumn<Reader, String> columnName;
    @FXML
    private TableColumn<Reader, String>  columnSurname;
    @FXML
    private TableColumn<Reader, String> columnPatronymic;
    @FXML
    private TableColumn<Reader, String> columnAdress;
    @FXML
    private TableColumn<Reader, String>  columnTelephone;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;

    public void actionClose(ActionEvent actionEvent)
    {
        Node sourse = (Node) actionEvent.getSource();
        Stage stage =(Stage)  sourse.getScene().getWindow();
        stage.hide();
    }

    public Reader getReader() {
        return reader;
    }

    @FXML
    public void actionSave(ActionEvent actionEvent) throws SQLException {

        reader = (Reader) tableReader.getSelectionModel().getSelectedItem();

        actionClose(actionEvent);

    }

    public void setReader(Reader reader) throws SQLException {
        this.reader = reader;
        columnID.setCellValueFactory(new PropertyValueFactory<Reader, Integer>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<Reader, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<Reader, String>("surname"));
        columnPatronymic.setCellValueFactory(new PropertyValueFactory<Reader,String>("patronymic"));
        columnAdress.setCellValueFactory(new PropertyValueFactory<Reader, String>("adress"));
        columnTelephone.setCellValueFactory(new PropertyValueFactory<Reader,String>("Telephone"));
        readerList.fillReaderdata();
        tableReader.setItems(readerList.getReaderList());
    }
}
