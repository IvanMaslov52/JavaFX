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

public class OrderController {
    private CollectionOrder orderList =  CollectionOrder.init();
    @FXML
    private TableView tableOrder;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button change;
    @FXML
    private TableColumn <Order, Integer> columnID;
    @FXML
    private TableColumn <Order, String>   columnGdata;
    @FXML
    private TableColumn <Order, String> columnRdata;
    @FXML
    private TableColumn <Order, Integer> columnReader;
    @FXML
    private TableColumn <Order, Integer> columnBook;

    private Stage orderStage;

    private Parent fxmlEdit;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private OrderEditor orderEditor;

    private Stage editOrderstage;
    @FXML
    private void initialize() throws SQLException {
        columnID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
        columnGdata.setCellValueFactory(new PropertyValueFactory<Order, String>("gdata"));
        columnRdata.setCellValueFactory(new PropertyValueFactory<Order,String>("rdata"));
        columnReader.setCellValueFactory(new PropertyValueFactory<Order,Integer>("reader"));
        columnBook.setCellValueFactory(new PropertyValueFactory<Order,  Integer>("book"));
        orderList.fillOrderdata();
        tableOrder.setItems(orderList.getOrderList());
        try {
            fxmlLoader.setLocation(getClass().getResource("order-new.fxml"));
            fxmlEdit = fxmlLoader.load();
            orderEditor = fxmlLoader.getController();
            orderEditor.setOrderstage(editOrderstage);
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
                Reader reader = new Reader(0, "","","","","");
                Book book = new Book(0, "","","","");
                orderEditor.setOrder(new Order(0,"","",reader,book));
                action();
                break;
            case "change":
                orderEditor.setOrder((Order) tableOrder.getSelectionModel().getSelectedItem());
                action();
                break;
            case "delete":
                orderList.deleteOrder((Order) tableOrder.getSelectionModel().getSelectedItem());
                break;
            default:

        }
    }
    private void action()
    {
        if(editOrderstage == null)
        {
            editOrderstage = new Stage();
            editOrderstage.setTitle("Change");
            editOrderstage.setScene(new Scene(fxmlEdit));
            editOrderstage.initModality(Modality.WINDOW_MODAL);
            editOrderstage.initOwner(orderStage);
        }
        editOrderstage.show();
    }

    public void setOrderStage(Stage bookStage) {
        this.orderStage = bookStage;
    }
}
