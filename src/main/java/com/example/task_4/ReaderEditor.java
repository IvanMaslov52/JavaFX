package com.example.task_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ReaderEditor {
    private CollectionReader readerlist = CollectionReader.init();
    private Reader reader;
    @FXML
    private Button cancel;

    @FXML
    private TextField Namefield;
    @FXML
    private TextField Surnamefield;
    @FXML
    private TextField Patronymicfield;
    @FXML
    private TextField Adressfield;
    @FXML
    private TextField Telephonefield;
    @FXML
    private Button ok;

    public Reader getReader() {
        return reader;
    }
    @FXML
    public void actionClose(ActionEvent actionEvent)
    {
        Node sourse = (Node) actionEvent.getSource();
        Stage stage =(Stage)  sourse.getScene().getWindow();
        stage.hide();
    }
    public void setReader(Reader reader)
    {
        this.reader = reader;
        Namefield.setText(reader.name.get());
        Surnamefield.setText(reader.surname.get());
        Patronymicfield.setText(reader.patronymic.get());
        Adressfield.setText(reader.adress.get());
        Telephonefield.setText(reader.telephone.get());

    }
    @FXML
    public void actionSave(ActionEvent actionEvent) throws SQLException
    {
        DBHandler dbHandler = new DBHandler();
        if(reader.getId() == 0)
        {
            reader.setId(CollectionReader.size);
            reader.setAdress(Adressfield.getText());
            reader.setName(Namefield.getText());
            reader.setSurname(Surnamefield.getText());
            reader.setTelephone(Telephonefield.getText());
            reader.setPatronymic(Patronymicfield.getText());
            readerlist.addReader(reader);
            dbHandler.insertreader(reader);
            CollectionReader.size++;
            actionClose(actionEvent);
        }
        reader.setAdress(Adressfield.getText());
        reader.setName(Namefield.getText());
        reader.setSurname(Surnamefield.getText());
        reader.setTelephone(Telephonefield.getText());
        reader.setPatronymic(Patronymicfield.getText());
        readerlist.updateReader(reader);
        actionClose(actionEvent);
    }
}
