package com.example.task_4;


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


public class ReaderController {
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
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button change;

    public Stage getReaderStage() {
        return readerStage;
    }
    public void setReaderStage(Stage readerStage) {
        this.readerStage = readerStage;
    }


    private Stage readerStage;
    private Parent fxmlEdit;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private ReaderEditor readerEditor;

    private Stage editReaderstage;

    @FXML
    private void initialize() throws SQLException {
        columnID.setCellValueFactory(new PropertyValueFactory<Reader, Integer>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<Reader, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<Reader, String>("surname"));
        columnPatronymic.setCellValueFactory(new PropertyValueFactory<Reader,String>("patronymic"));
        columnAdress.setCellValueFactory(new PropertyValueFactory<Reader, String>("adress"));
        columnTelephone.setCellValueFactory(new PropertyValueFactory<Reader,String>("Telephone"));
        readerList.fillReaderdata();
        tableReader.setItems(readerList.getReaderList());
        try {
            fxmlLoader.setLocation(getClass().getResource("reader-new.fxml"));
            fxmlEdit = fxmlLoader.load();
            readerEditor = fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
 @FXML
    public void showDialog(ActionEvent actionEvent) throws Exception {
        Object source = actionEvent.getSource();

        if(!(source instanceof Button))
        {
            return;
        }
        Button clickedButton = (Button) source;
        switch (clickedButton.getId())
        {
            case "add":
                readerEditor.setReader(new Reader(0,"","","","",""));
                action();
                break;
            case "change":
                readerEditor.setReader((Reader) tableReader.getSelectionModel().getSelectedItem());
                action();
                break;
            case "delete":
                readerList.deleteReader((Reader) tableReader.getSelectionModel().getSelectedItem());
                break;
        }
    }
    private void action()
    {
        if(editReaderstage == null)
        {
            editReaderstage = new Stage();
            editReaderstage.setTitle("Change");
            editReaderstage.setScene(new Scene(fxmlEdit));
            editReaderstage.initModality(Modality.WINDOW_MODAL);
            editReaderstage.initOwner(readerStage);
        }
        editReaderstage.showAndWait();
    }





}
