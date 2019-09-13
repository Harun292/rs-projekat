package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class StudentController {

    public TableColumn<Grades, String> classColumn;
    public TableColumn pointsColumn;
    public TableColumn gradeColumn;
    public TableView<Grades> studentTableView;
    private ObservableList<Grades> students;
    private Student student;
    Model model=Model.getInstance();

    public StudentController(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @FXML
    public void initialize() {
        System.out.println(student.getId());
        if(model.getById(student.getId())!=null) {
            students = FXCollections.observableArrayList(model.getById(student.getId()).getGrades());
            System.out.println(model.getById(student.getId()).toString());
            studentTableView.setItems(students);
            pointsColumn.setCellValueFactory(new PropertyValueFactory("numberOfPoints"));
            gradeColumn.setCellValueFactory(new PropertyValueFactory("grade"));
            classColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSubject().getSubjectName()));
        }
    }
}
