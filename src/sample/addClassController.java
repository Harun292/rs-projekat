package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class addClassController {
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public ChoiceBox<Professor> addClassChoice;
    public TextField addClassField;
    ObservableList<Professor> professors = FXCollections.observableArrayList();
    Model model=Model.getInstance();
    boolean ok,ok1;

    @FXML
    public void initialize()
    {
        addClassField.getStyleClass().addAll("invalid");
        professors = model.getProfessors();
        addClassChoice.setItems(professors);
        addClassField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (addClassField.getText().isEmpty()) {
                addClassField.getStyleClass().addAll("invalid");
                ok=false;
            } else {
                addClassField.getStyleClass().removeAll("invalid");
                ok=true;
            }
        });
        addClassChoice.valueProperty().addListener((obs, oldIme, newIme) -> {
            if (addClassChoice.getValue().getName().equals("")) {
                ok1=false;
            } else {
                ok1=true;
            }
        });

    }

    public void okAction(ActionEvent actionEvent) throws SQLException {
        if(ok&&ok1) {
            subject = new Subject();
            subject.setProfessor(addClassChoice.getSelectionModel().getSelectedItem());
            subject.setSubjectName(addClassField.getText());
            model.addSubjectBase(subject);
            Stage stage = (Stage) addClassField.getScene().getWindow();
            stage.close();
        }
        else if(!ok&&ok1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter subject name!");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
        }
        else if(ok&&!ok1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please choose professor!");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid input!");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        addClassField.getScene().getWindow().hide();
    }
}
