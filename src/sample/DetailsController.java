package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class DetailsController {


    public TableView<Grades> studentTableView;
    public TableColumn<Grades,String> classColumn;
    public TableColumn pointsColumn;
    public TableColumn gradeColumn;
    private Student student;
    ObservableList<Grades> grades;
    Model model=Model.getInstance();

    public DetailsController(Student student) {
        this.student=student;
    }

    @FXML
    public void initialize() throws SQLException {
        grades=FXCollections.observableArrayList(student.getGrades());
        studentTableView.setItems(grades);
        pointsColumn.setCellValueFactory(new PropertyValueFactory("numberOfPoints"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory("grade"));
        classColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSubject().getSubjectName()));
    }
    public void addDetails(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addDetailsPanel.fxml"));
        addDetailsController detailsController =new addDetailsController();
        loader.setController(detailsController);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Details");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnHiding(event -> {
            Grades grade = detailsController.getGrade();
            if (grade != null) {
                model.getById(student.getId()).getGrades().add(grade);
                grades=FXCollections.observableArrayList(model.getById(student.getId()).getGrades());
                studentTableView.setItems(grades);
                try {
                    model.addGradeBase(student,grade);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                studentTableView.refresh();
            }
        });

    }
    public void deleteDetails() throws SQLException {
        if(studentTableView.getSelectionModel().getSelectedItem()!=null){

            model.deleteGradeBase(student,studentTableView.getSelectionModel().getSelectedItem());
            model.getById(student.getId()).getGrades().remove(studentTableView.getSelectionModel().getSelectedItem());
            grades=FXCollections.observableArrayList(model.getById(student.getId()).getGrades());
            studentTableView.setItems(grades);
            studentTableView.refresh();
        }
        else return;
    }





}
