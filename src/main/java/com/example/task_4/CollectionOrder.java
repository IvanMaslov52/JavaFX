package com.example.task_4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CollectionOrder {
    private static CollectionOrder collectionOrder;
    private static ObservableList<Order> orderList = FXCollections.observableArrayList();
    public static int size;
    private CollectionOrder()
    {}
    public static CollectionOrder init()
    {
        if(collectionOrder == null)
            collectionOrder = new CollectionOrder();
        return collectionOrder;

    }
    public void addOrder(Order  order)
    {
        if(order.getId() !=0)
        orderList.add(order);
    }

    public void udpateOrder(Order order) throws SQLException {
        DBHandler dbHandler  = new DBHandler();
        dbHandler.updateorder(order);

    }

    public void deleteOrder(Order order) throws SQLException {
        DBHandler dbHandler =new DBHandler();
        orderList.remove(order);
        dbHandler.deleteorder(order);
    }
    public ObservableList<Order> getOrderList()
    {
        return orderList;
    }
    public void fillOrderdata() throws SQLException {
        DBHandler dbHandler = new DBHandler();

        if(orderList.size() == 0)
        {
            dbHandler.selectorder();
            if(orderList.size() == 0)
                size = 1;
            else
                size = orderList.get(orderList.size()-1).getId()+1;
        }
        else
        {
            orderList.clear();
            dbHandler.selectorder();

        }
    }

}
