package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
        Person user=new Person();
        user.setName(userField.getText());
        for (Person person:model.getUsers()) {
            if(user.getName().equals(person.getName()))
            {
                if(person instanceof Professor) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("adminPanel.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("E-Index");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        Student student=new Student();
                        for (Student stud:model.getStudents()) {
                            if(stud.getName().equals(userField.getText()))
                            {
                                student=stud;
                                break;
                            }
                        }
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentPanel.fxml"));
                        StudentController studentController=new StudentController(student);
                        loader.setController(studentController);
                        Parent root=loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("E-Index");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        }


    }


}
