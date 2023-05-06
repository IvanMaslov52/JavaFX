package com.example.task_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class OrderEditor {
    private Order order;

    private CollectionOrder listorder =CollectionOrder.init();
    private CollectionBook listbook = CollectionBook.init();
    private CollectionReader listreader = CollectionReader.init();
    @FXML
    private Button cancel;
    @FXML
    private Button btnbook;
    @FXML
    private Button btnreader;
    @FXML
    private TextField Gdatafield;
    @FXML
    private TextField Rdatafield;
    @FXML
    private Label booklabel;
    @FXML
    private Label readerlabel;
    private BookAdd bookAdd;

    private ReaderAdd readerAdd;
    @FXML
    private Button ok;
    private Stage orderstage;
    private FXMLLoader fxmlbook = new FXMLLoader();
    private Parent bookparent;
    private FXMLLoader fxmlreader = new FXMLLoader();
    private Parent readerparent;
    private Stage bookstage;
    private Stage readerstage;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        Gdatafield.setText(order.gdata.get());
        Rdatafield.setText(order.rdata.get());
        this.order = order;
        readerlabel.setText(order.reader.toString());
        booklabel.setText(order.book.toString());
    }
    public void actionClose(ActionEvent actionEvent)
    {
        Node sourse = (Node) actionEvent.getSource();
        Stage stage =(Stage)  sourse.getScene().getWindow();
        stage.hide();
    }
    public void setReader(Order order) throws SQLException {
        this.order = order;


    }
    @FXML
    public void actionSave(ActionEvent actionEvent) throws SQLException {

        DBHandler dbHandler= new DBHandler();
        if(order.getId()== 0 )
        {
        order.setId(CollectionOrder.size);
        order.setGdata(Gdatafield.getText());
        order.setRdata(Rdatafield.getText());
        order.setReader(readerAdd.getReader());
        order.setBook(bookAdd.getBook());
        listorder.addOrder(order);
        dbHandler.insertorder(order);
        CollectionOrder.size++;
        actionClose(actionEvent);
        }
            order.setGdata(Gdatafield.getText());
            order.setRdata(Rdatafield.getText());
            if(readerAdd.getReader() != null)
            order.setReader(readerAdd.getReader());
        if(bookAdd.getBook() != null)
            order.setBook(bookAdd.getBook());
            listorder.udpateOrder(order);
            actionClose(actionEvent);

    }

    public void setOrderstage(Stage orderstage) {
        this.orderstage = orderstage;
    }
    public void actionbook(ActionEvent actionEvent) throws SQLException {

        bookAdd.setBook(order.book);
        if(bookstage == null)
       {
            bookstage = new Stage();
            bookstage.setTitle("Change");
            bookstage.setScene(new Scene(bookparent));
            bookstage.initModality(Modality.WINDOW_MODAL);
            bookstage.initOwner(orderstage);
       }
        bookstage.showAndWait();
        booklabel.setText(bookAdd.getBook().toString());

    }
    public void actionreader(ActionEvent actionEvent) throws SQLException {

        readerAdd.setReader(order.reader);
        if(readerstage == null)
        {
            readerstage = new Stage();
            readerstage.setTitle("Change");
            readerstage.setScene(new Scene(readerparent));
            readerstage.initModality(Modality.WINDOW_MODAL);
            readerstage.initOwner(orderstage);
      }
        readerstage.showAndWait();
        readerlabel.setText(readerAdd.getReader().toString());




    }

    public void initialize()
    {

        try {
            fxmlbook.setLocation(getClass().getResource("book-add.fxml"));
            bookparent = fxmlbook.load();
            bookAdd = fxmlbook.getController();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
            fxmlreader.setLocation(getClass().getResource("reader-add.fxml"));
            readerparent = fxmlreader.load();
            readerAdd = fxmlreader.getController();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }








}
