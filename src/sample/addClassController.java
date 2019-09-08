package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addClassController {
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public ChoiceBox<Professor> addClassChoice;
    public TextField addClassField;
    ObservableList<Professor> professors = FXCollections.observableArrayList();
    Model model = new Model();

    @FXML
    public void initialize()
    {
        model.load();
        professors = model.getProfessors();
        addClassChoice.setItems(professors);

    }


    public void okAction(ActionEvent actionEvent) {
        subject=new Subject();
        subject.setProfessor(addClassChoice.getSelectionModel().getSelectedItem());
        subject.setSubjectName(addClassField.getText());
        Stage stage = (Stage) addClassField.getScene().getWindow();
        stage.close();
    }

    public void cancelAction(ActionEvent actionEvent) {
    }
}
