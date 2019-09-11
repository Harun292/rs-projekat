package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class addDetailsController {
    public TextField addDetailsField;
    public TextField addGradeField;
    public ChoiceBox<Subject> cbAddSubject;
    private Grades grade;
    Model model=Model.getInstance();
    boolean ok,ok1,ok2;

    public Grades getGrade() {
        return grade;
    }

    public void setGrade(Grades grade) {
        this.grade = grade;
    }
    @FXML
        public void initialize()
    {
        addGradeField.getStyleClass().addAll("invalid");
        addDetailsField.getStyleClass().addAll("invalid");
        cbAddSubject.setItems(model.getSubjects());
        addDetailsField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (addDetailsField.getText().isEmpty()) {
                addDetailsField.getStyleClass().addAll("invalid");
                ok=false;
            } else {
                addDetailsField.getStyleClass().removeAll("invalid");
                ok=true;
            }
        });
        addGradeField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (addGradeField.getText().isEmpty()) {
                addGradeField.getStyleClass().addAll("invalid");
                ok1=false;
            } else {
                addGradeField.getStyleClass().removeAll("invalid");
                ok1=true;
            }
        });
        cbAddSubject.valueProperty().addListener((obs, oldIme, newIme) -> {
            if (cbAddSubject.getValue().getSubjectName().equals("")) {
                ok2=false;
            } else {
                ok2=true;
            }
        });

    }
    public void okAction() {
        if (ok && ok1 && ok2) {
            grade = new Grades();
            grade.setSubject(cbAddSubject.getSelectionModel().getSelectedItem());
            grade.setNumberOfPoints(Integer.parseInt(addDetailsField.getText()));
            grade.setGrade(Integer.parseInt(addGradeField.getText()));
            Stage stage = (Stage) addGradeField.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid input!");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    public void cancelAction()
    {
        addGradeField.getScene().getWindow().hide();
    }

}
