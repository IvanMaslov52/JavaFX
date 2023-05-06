package com.example.task_4;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Reader
{
    SimpleIntegerProperty id;
    SimpleStringProperty name;
    SimpleStringProperty surname;
    SimpleStringProperty patronymic;
    SimpleStringProperty adress;
    SimpleStringProperty telephone;
    public Reader(int id, String name, String surname, String patronymic, String adress, String telephone)
    {
        this.id = new SimpleIntegerProperty(id);
        this.name  = new SimpleStringProperty(name);
        this.surname =new SimpleStringProperty(surname) ;
        this.patronymic = new SimpleStringProperty(patronymic);
        this.adress =new SimpleStringProperty(adress);
        this.telephone =new SimpleStringProperty(telephone);
    }

    public int getId(){return id.get();}

    public String getTelephone() {
        return telephone.get();
    }

    public String getAdress() {
        return adress.get();
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public String getSurname() {
        return surname.get();
    }

    public String getName() {
        return name.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
    }

    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id.get() +
                ", \"name\": \"" + name.get() + '\"' +
                ", \"surname\": \"" + surname.get() + '\"' +
                ", \"patronymic\": \"" + patronymic.get() + '\"' +
                ", \"adress\": \"" + adress.get() + '\"' +
                ", \"telephone\": \"" + telephone.get() + '\"' +
                '}';
    }
}
