package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addDetailsController {
    public TextField addDetailsField;
    public TextField addGradeField;
    public ChoiceBox<Subject> cbAddSubject;
    private Grades grade;
    Model model=Model.getInstance();

    public Grades getGrade() {
        return grade;
    }

    public void setGrade(Grades grade) {
        this.grade = grade;
    }
    @FXML
        public void initialize()
    {
        cbAddSubject.setItems(model.getSubjects());
    }
    public void okAction()
    {
        grade=new Grades();
        grade.setSubject(cbAddSubject.getSelectionModel().getSelectedItem());
        grade.setNumberOfPoints(Integer.parseInt(addDetailsField.getText()));
        grade.setGrade(Integer.parseInt(addGradeField.getText()));
        Stage stage = (Stage) addGradeField.getScene().getWindow();
        stage.close();
    }
    public void cancelAction()
    {

    }

}
