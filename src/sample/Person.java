package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Person {
    SimpleStringProperty name= new SimpleStringProperty("");
    SimpleStringProperty surname= new SimpleStringProperty("");
    SimpleStringProperty jmbg= new SimpleStringProperty("");
    SimpleStringProperty placeOfBirth= new SimpleStringProperty("");
    SimpleStringProperty livingPlace= new SimpleStringProperty("");
    SimpleObjectProperty<LocalDate> dateOfBirth=new SimpleObjectProperty<>();
    SimpleStringProperty username=new SimpleStringProperty();
    SimpleStringProperty password=new SimpleStringProperty();

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person() {
    }

    public Person(String name, String surname, String jmbg, String placeOfBirth, String livingPlace, LocalDate dateOfBirth,int id,String username,String password) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.jmbg = new SimpleStringProperty(jmbg);
        this.placeOfBirth = new SimpleStringProperty(placeOfBirth);
        this.livingPlace = new SimpleStringProperty(livingPlace);
        this.dateOfBirth =new SimpleObjectProperty<LocalDate>(dateOfBirth);
        this.id=id;
        this.username=new SimpleStringProperty(username);
        this.password=new SimpleStringProperty(password);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getJmbg() {
        return jmbg.get();
    }

    public SimpleStringProperty jmbgProperty() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg.set(jmbg);
    }

    public String getPlaceOfBirth() {
        return placeOfBirth.get();
    }

    public SimpleStringProperty placeOfBirthProperty() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth.set(placeOfBirth);
    }

    public String getLivingPlace() {
        return livingPlace.get();
    }

    public SimpleStringProperty livingPlaceProperty() {
        return livingPlace;
    }

    public void setLivingPlace(String livingPlace) {
        this.livingPlace.set(livingPlace);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth.get();
    }

    public SimpleObjectProperty<LocalDate> dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public String toString() {
        return name.get()+" "+surname.get();
    }
}
