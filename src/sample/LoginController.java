package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class LoginController {
    public TextField userField;
    public PasswordField passField;
    private Model model;

    public LoginController() {
        model = Model.getInstance();
    }

    @FXML
    public void initialize()
    {
        try {
            model.getPersons();
            model.getSubjectsBase();
            model.getGrades();
            for (Professor prof:model.getProfessors()) {
                for (Subject sub:model.getSubjects()) {
                    if(prof.getId()==sub.getProfessor().getId())
                    {
                        prof.getSubjects().add(sub);
                    }
                }
            }
            for (Grades grade:model.getGrades()) {
                for (Student stud:model.getStudents()) {
                    if(stud.getId()==grade.getId())
                    {
                        stud.getGrades().add(grade);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        passField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    logAction();
                }
            }
        });
    }


    @FXML
    void logAction (){
        Person person=model.findByUsername(userField.getText().trim());
            if(person!=null&&person instanceof Professor&&person.getPassword().equals(passField.getText().trim()))
            {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/fxml/adminPanel.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("E-Index");
                        stage.setScene(new Scene(root));
                        stage.setResizable(false);
                        //userField.getScene().getWindow().hide();
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(person!=null&&person.getPassword().equals(passField.getText().trim())){
                    try {
                        Student student=(Student) person;
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/studentPanel.fxml"));
                        StudentController studentController=new StudentController(student);
                        loader.setController(studentController);
                        Parent root=loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("E-Index");
                        stage.setScene(new Scene(root));
                        stage.setResizable(false);
                        //userField.getScene().getWindow().hide();
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid username or password");
                        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                        alert.getButtonTypes().setAll(buttonTypeCancel);
                        Optional<ButtonType> result = alert.showAndWait();

                    }



    }


}
