package sample;

import javafx.beans.property.SimpleIntegerProperty;

public class Grades {
    SimpleIntegerProperty numberOfPoints=new SimpleIntegerProperty();
    SimpleIntegerProperty grade=new SimpleIntegerProperty();
    Subject subject =new Subject();
    int id,studentId;
    Model model=Model.getInstance();


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Grades() {
        this.id=model.getNextGradeId();
    }

    public int getNumberOfPoints() {
        return numberOfPoints.get();
    }

    public SimpleIntegerProperty numberOfPointsProperty() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints.set(numberOfPoints);
    }

    public int getGrade() {
        return grade.get();
    }

    public SimpleIntegerProperty gradeProperty() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

