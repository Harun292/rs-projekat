package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to delete grade: " + studentTableView.getSelectionModel().getSelectedItem().getSubject().toString());
            ButtonType buttonType = new ButtonType("Yes");
            ButtonType buttonType1=new ButtonType("No");
            alert.getButtonTypes().setAll(buttonType,buttonType1);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()==buttonType) {
                model.deleteGradeBase(student,studentTableView.getSelectionModel().getSelectedItem());
                model.getById(student.getId()).getGrades().remove(studentTableView.getSelectionModel().getSelectedItem());
                grades=FXCollections.observableArrayList(model.getById(student.getId()).getGrades());
                studentTableView.setItems(grades);
                studentTableView.refresh();
            }
            else
                alert.close();
        }

        else {
            return;

        }
    }


    public void generateTxt()
    {
        if(student.getGrades().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Report not generated");
            alert.setHeaderText("No appointments in table");
            alert.showAndWait();
            return;
        }
        PrintWriter output = null;
        FileChooser chooser = new FileChooser();
        chooser.setTitle("JavaFX Projects");
        File defaultDirectory = new File(System.getProperty("user.home"));
        chooser.setInitialDirectory(defaultDirectory);
        File file = chooser.showSaveDialog(new Stage());
        try {
            FileWriter writer = new FileWriter(file, false);
            output = new PrintWriter(writer);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Report not generated");
            alert.setHeaderText("Report could not be generated at " + file.getAbsolutePath());
            alert.showAndWait();
            e.printStackTrace();
        }
        if(output != null) {
            for (Grades student:student.getGrades()) {
                output.println (String.format("%3d" , student.getNumberOfPoints()) + " " +
                        String.format("%3d",student.getGrade()) + " " + String.format("%-20s",student.getSubject().toString()));
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Report generated");
            alert.setHeaderText("Report generated at " + file.getAbsolutePath());
            alert.showAndWait();
            output.close();
        }
    }



}
