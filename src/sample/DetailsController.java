package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DetailsController {


    public TableView<Grades> studentTableView;
    public TableColumn<Grades,String> classColumn;
    public TableColumn pointsColumn;
    public TableColumn gradeColumn;
    ObservableList<Grades> students;

    @FXML
    public void initialize() {
        Model model=new Model();
        model.load();
        students = FXCollections.observableArrayList(model.getPerson().getGrades());
        studentTableView.setItems(students);
        pointsColumn.setCellValueFactory(new PropertyValueFactory("numberOfPoints"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory("grade"));
        classColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSubject().getSubjectName()));
    }





}
