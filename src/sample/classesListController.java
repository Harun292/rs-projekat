package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class classesListController {
    public ListView classesListView;
    private ObservableList<Subject> subjects;
    @FXML
        public void initialize()
    {
        Model model=new Model();
        model.load();
        subjects=FXCollections.observableArrayList(model.getSubjects());
        ObservableList<Subject> chosen=FXCollections.observableArrayList();
        for (Subject subject:subjects) {
            if(model.getPerson().getJmbg().equals(subject.getProfessor().getJmbg()))
            {
                chosen.add(subject);
            }
        }
        System.out.println(chosen.get(0));
        classesListView.setItems(chosen);

    }




}
