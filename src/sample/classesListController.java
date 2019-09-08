package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class classesListController {
    public  ListView<Subject> classesList;
    private ObservableList<Subject> subjects = FXCollections.observableArrayList();
    private Professor professor = new Professor();


    @FXML
    public void initialize() {
        for (Subject subject: professor.getSubjects()) {
            subjects.add(subject);
        }
        classesList.setItems(subjects);

    }

    public classesListController(Professor professor) {
        this.professor = professor;
    }



}
