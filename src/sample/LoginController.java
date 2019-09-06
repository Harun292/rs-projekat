package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    public TextField userField;
    public PasswordField passField;
    @FXML
    void logAction (){
        Model model=new Model();
        model.load();
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
                        Parent root = FXMLLoader.load(getClass().getResource("studentPanel.fxml"));
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
