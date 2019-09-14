package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Person {
    SimpleStringProperty mothersName=new SimpleStringProperty("");
    SimpleStringProperty fathersName=new SimpleStringProperty("");
    SimpleIntegerProperty index=new SimpleIntegerProperty();
    ArrayList<Grades> grades=new ArrayList<>();

    public ArrayList<Grades> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grades> grades) {
        this.grades = grades;
    }

    public Student() {
        this.id=model.getNextUserId();
    }

    public Student(String name, String surname, String jmbg, String placeOfBirth, String livingPlace, LocalDate dateOfBirth, String mothersName, String fathersName, int index,int id,String username,String password) {
        super(name, surname, jmbg, placeOfBirth, livingPlace, dateOfBirth,id,username,password);
        this.mothersName =new SimpleStringProperty(mothersName);
        this.fathersName = new SimpleStringProperty(fathersName);
        this.index = new SimpleIntegerProperty(index);
    }

    public String getMothersName() {
        return mothersName.get();
    }

    public SimpleStringProperty mothersNameProperty() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName.set(mothersName);
    }

    public String getFathersName() {
        return fathersName.get();
    }

    public SimpleStringProperty fathersNameProperty() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName.set(fathersName);
    }

    public int getIndex() {
        return index.get();
    }

    public SimpleIntegerProperty indexProperty() {
        return index;
    }

    public void setIndex(int index) {
        this.index.set(index);
    }

}
