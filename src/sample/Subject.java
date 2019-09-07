package sample;

import javafx.beans.property.SimpleStringProperty;

public class Subject {
    SimpleStringProperty subjectName=new SimpleStringProperty();
    int id;
    Professor professor=new Professor();

    public Subject() {
    }

    public Subject(String subjectName, int id, Professor professor) {
        this.subjectName = new SimpleStringProperty(subjectName);
        this.id = id;
        this.professor = professor;
    }

    public String getSubjectName() {
        return subjectName.get();
    }

    public SimpleStringProperty subjectNameProperty() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName.set(subjectName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return subjectName.get();
    }
}
