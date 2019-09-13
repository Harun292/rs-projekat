package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class Professor extends Person {
    ObservableList<Subject> subjects= FXCollections.observableArrayList();

    public Professor() {
    }

    public Professor(String name, String surname, String jmbg, String placeOfBirth, String livingPlace, LocalDate dateOfBirth, int id, String username, String password)
    {
        super(name,surname,jmbg,placeOfBirth,livingPlace,dateOfBirth,id,username,password);
    }
    public ObservableList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ObservableList<Subject> subjects) {
        this.subjects = subjects;
    }
}
