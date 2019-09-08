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
    private ObservableList<Grades> student;


    @FXML
    public void initialize() {
        Model model=new Model();
        model.load();
        student = FXCollections.observableArrayList((((Student) model.getPerson()).getGrades()));
        studentTableView.setItems(student);
        pointsColumn.setCellValueFactory(new PropertyValueFactory("numberOfPoints"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory("grade"));
        classColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSubject().getSubjectName()));
    }
}
