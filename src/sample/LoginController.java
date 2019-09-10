package sample;

import javafx.application.Platform;
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
        Person person=model.findByUsername(userField.getText().trim());
            if(person!=null&&person instanceof Professor&&person.getPassword().equals(passField.getText().trim()))
            {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("adminPanel.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("E-Index");
                        stage.setScene(new Scene(root));
                        userField.getScene().getWindow().hide();
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(person!=null&&person.getPassword().equals(passField.getText().trim())){
                    try {
                        Student student=new Student();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentPanel.fxml"));
                        StudentController studentController=new StudentController(student);
                        loader.setController(studentController);
                        Parent root=loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("E-Index");
                        stage.setScene(new Scene(root));
                        userField.getScene().getWindow().hide();
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                    {
                        //VALIDACIJA
                    }



    }


}
