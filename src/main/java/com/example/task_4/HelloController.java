package com.example.task_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloController {

    @FXML
    public void showauthor(ActionEvent event){
try {
    Stage stage = new Stage();
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("reader-view.fxml"));
    Parent root = fxmlLoader.load();
    ReaderController readerController = fxmlLoader.getController();
    readerController.setReaderStage(stage);
    stage.setTitle("Редактирование");
    stage.setMinHeight(150);
    stage.setMinWidth(300);
    stage.setResizable(false);
    stage.setScene(new Scene(root));
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(((Node) event.getSource()).getScene().getWindow());
    stage.show();
}
catch (IOException e)
{
    e.printStackTrace();
}

    }
    public void showbook(ActionEvent event){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("book-view.fxml"));
            Parent root = fxmlLoader.load();
            BookController bookController =fxmlLoader.getController();
            bookController.setBookStage(stage);
            stage.setTitle("Редактирование");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());

            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void showorder(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("order-view.fxml"));
            Parent root = fxmlLoader.load();
            OrderController orderController = fxmlLoader.getController();
            orderController.setOrderStage(stage);
            stage.setTitle("Редактирование");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
