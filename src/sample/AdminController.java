package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class AdminController {

    public ListView<Student> userStudentList;
    public ListView<Professor> userProfessorList;
    public TableView<Subject> classesTable;
    public TableColumn<Subject,String> classColumn;
    public TableColumn<Subject,String> professorColumn;
    public ListView<Professor> professorList;
    public TextField professorJmbgField;
    public TextField professorBirthPlaceField;
    public TextField professorSurnameField;
    public TextField professorLivingPlaceField;
    public DatePicker professorDatePicker;
    public TextField professorNameField;
    public ListView<Student> studentsList;
    public ImageView imageView;
    public TextField nameField;
    public TextField surnameField;
    public TextField indexField;
    public TextField jmbgField;
    public DatePicker datePicker;
    public TextField birthPlaceField;
    public TextField livingPlaceField;
    public TextField fatherField;
    public TextField motherField;
    public TextField userNameField;
    public TextField passwordField;
    public TextField passwordFieldStud;
    public TextField userNameFieldStud;
    public Tab studentsTab;
    public Tab professorsTab;
    public TabPane adminTabPane;
    public Image image;
    Model model=Model.getInstance();
    ObservableList<Subject> subjects=FXCollections.observableArrayList();
    ObservableList<Student> students=FXCollections.observableArrayList();
    ObservableList<Professor> professors=FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        try {
            image=new Image(new FileInputStream("C:\\Users\\ESAD-PC\\IdeaProjects\\E-Index\\resources\\images\\rs.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        students=FXCollections.observableArrayList(model.getStudents());
        professors=FXCollections.observableArrayList(model.getProfessors());

        userStudentList.setItems(model.getStudents());
        userProfessorList.setItems(model.getProfessors());
        subjects=FXCollections.observableArrayList(model.getSubjects());
        classesTable.setItems(model.getSubjects());
        classColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getSubjectName()));
        professorColumn.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getProfessor().toString()));

        professorList.setItems(model.getProfessors());
        professorList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null) {
                professorNameField.textProperty().unbindBidirectional(oldValue.nameProperty());
                professorSurnameField.textProperty().unbindBidirectional(oldValue.surnameProperty());
                professorDatePicker.valueProperty().unbindBidirectional(oldValue.dateOfBirthProperty());
                professorBirthPlaceField.textProperty().unbindBidirectional(oldValue.placeOfBirthProperty());
                professorJmbgField.textProperty().unbindBidirectional(oldValue.jmbgProperty());
                professorLivingPlaceField.textProperty().unbindBidirectional(oldValue.livingPlaceProperty());
                userNameField.textProperty().unbindBidirectional(oldValue.usernameProperty());
                passwordField.textProperty().unbindBidirectional(oldValue.passwordProperty());
            }
                if (newValue == null) {
                professorNameField.setText("");
                professorSurnameField.setText("");
                professorDatePicker.setValue(LocalDate.now());
                professorBirthPlaceField.setText("");
                professorJmbgField.setText("0");
                professorLivingPlaceField.setText("");
                userNameField.setText("");
                passwordField.setText("");
            } else {
                    professorNameField.textProperty().bindBidirectional(newValue.nameProperty());
                    professorSurnameField.textProperty().bindBidirectional(newValue.surnameProperty());
                    professorDatePicker.valueProperty().bindBidirectional(newValue.dateOfBirthProperty());
                    professorBirthPlaceField.textProperty().bindBidirectional(newValue.placeOfBirthProperty());
                    professorJmbgField.textProperty().bindBidirectional(newValue.jmbgProperty());
                    professorLivingPlaceField.textProperty().bindBidirectional(newValue.livingPlaceProperty());
                    userNameField.textProperty().bindBidirectional(newValue.usernameProperty());
                    passwordField.textProperty().bindBidirectional(newValue.passwordProperty());
            }
                classesTable.refresh();
                userProfessorList.refresh();
                professorList.refresh();

        });
        studentsList.setItems(model.getStudents());
        studentsList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null) {
                nameField.textProperty().unbindBidirectional(oldValue.nameProperty());
                surnameField.textProperty().unbindBidirectional(oldValue.surnameProperty());
                indexField.textProperty().unbindBidirectional(oldValue.indexProperty());
                jmbgField.textProperty().unbindBidirectional(oldValue.jmbgProperty());
                datePicker.valueProperty().unbindBidirectional(oldValue.dateOfBirthProperty());
                birthPlaceField.textProperty().unbindBidirectional(oldValue.placeOfBirthProperty());
                livingPlaceField.textProperty().unbindBidirectional(oldValue.livingPlaceProperty());
                motherField.textProperty().unbindBidirectional(oldValue.mothersNameProperty());
                fatherField.textProperty().unbindBidirectional(oldValue.fathersNameProperty());
                userNameFieldStud.textProperty().unbindBidirectional(oldValue.usernameProperty());
                passwordFieldStud.textProperty().unbindBidirectional(oldValue.passwordProperty());
            }
            if (newValue == null) {
                nameField.setText("");
                surnameField.setText("");
                datePicker.setValue(LocalDate.now());
                indexField.setText("");
                jmbgField.setText("0");
                birthPlaceField.setText("");
                livingPlaceField.setText("");
                fatherField.setText("");
                motherField.setText("");
                userNameFieldStud.setText("");
                passwordFieldStud.setText("");

            } else {
                nameField.textProperty().bindBidirectional(newValue.nameProperty());
                surnameField.textProperty().bindBidirectional(newValue.surnameProperty());
                indexField.textProperty().bindBidirectional(newValue.indexProperty(),new NumberStringConverter());
                jmbgField.textProperty().bindBidirectional(newValue.jmbgProperty());
                datePicker.valueProperty().bindBidirectional(newValue.dateOfBirthProperty());
                birthPlaceField.textProperty().bindBidirectional(newValue.placeOfBirthProperty());
                livingPlaceField.textProperty().bindBidirectional(newValue.livingPlaceProperty());
                motherField.textProperty().bindBidirectional(newValue.mothersNameProperty());
                fatherField.textProperty().bindBidirectional(newValue.fathersNameProperty());
                userNameFieldStud.textProperty().bindBidirectional(newValue.usernameProperty());
                passwordFieldStud.textProperty().bindBidirectional(newValue.passwordProperty());
                imageView.setImage(image);
            }
            userStudentList.refresh();

        });
        nameField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(studentsList.getSelectionModel().getSelectedItem().getName())) {
                nameField.getStyleClass().removeAll("invalid");
                studentsList.getSelectionModel().getSelectedItem().setName(newIme);
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if(!newIme.isEmpty()) {
                nameField.getStyleClass().removeAll("invalid");
            }
            else nameField.getStyleClass().addAll("invalid");
        });
        surnameField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(studentsList.getSelectionModel().getSelectedItem().getSurname())) {
                surnameField.getStyleClass().removeAll("invalid");
                studentsList.getSelectionModel().getSelectedItem().setSurname(newIme);
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if (!newIme.isEmpty()) {
                surnameField.getStyleClass().removeAll("invalid");
            }
            else surnameField.getStyleClass().addAll("invalid");
        });
        indexField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(studentsList.getSelectionModel().getSelectedItem().getIndex())) {
                indexField.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }}
           if (!newIme.isEmpty()) {
                    indexField.getStyleClass().removeAll("invalid");
                }
                else indexField.getStyleClass().addAll("invalid");
        });

        userNameFieldStud.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(studentsList.getSelectionModel().getSelectedItem().getUsername())) {
                userNameFieldStud.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }}
           if(!newIme.isEmpty()) {
                    userNameFieldStud.getStyleClass().removeAll("invalid");
                }
                else userNameFieldStud.getStyleClass().addAll("invalid");
        });


        passwordFieldStud.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(studentsList.getSelectionModel().getSelectedItem().getPassword())) {
                passwordFieldStud.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if(!newIme.isEmpty()) {
                passwordFieldStud.getStyleClass().removeAll("invalid");
            }
            else passwordFieldStud.getStyleClass().addAll("invalid");
        });

        jmbgField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(studentsList.getSelectionModel().getSelectedItem().getJmbg())) {
                jmbgField.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }  if(!newIme.isEmpty()) {
                jmbgField.getStyleClass().removeAll("invalid");
            }
            else jmbgField.getStyleClass().addAll("invalid");
        });
        livingPlaceField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(studentsList.getSelectionModel().getSelectedItem().getLivingPlace())) {
                livingPlaceField.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if(!newIme.isEmpty()) {
                livingPlaceField.getStyleClass().removeAll("invalid");
            }
            else livingPlaceField.getStyleClass().addAll("invalid");
        });
        birthPlaceField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(studentsList.getSelectionModel().getSelectedItem().getPlaceOfBirth())) {
                birthPlaceField.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if(!newIme.isEmpty()) {
                birthPlaceField.getStyleClass().removeAll("invalid");
            }
            else birthPlaceField.getStyleClass().addAll("invalid");
        });

        motherField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(studentsList.getSelectionModel().getSelectedItem().getMothersName())) {
                motherField.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if(!newIme.isEmpty()) {
                motherField.getStyleClass().removeAll("invalid");
            }
            else motherField.getStyleClass().addAll("invalid");
        });

        fatherField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(studentsList.getSelectionModel().getSelectedItem().getFathersName())) {
                fatherField.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if(!newIme.isEmpty()) {
                fatherField.getStyleClass().removeAll("invalid");
            }
            else fatherField.getStyleClass().addAll("invalid");
        });
        datePicker.valueProperty().addListener((observableValue, localDate, t1) -> {
            if(datePicker.getValue()!=null&&!t1.equals(studentsList.getSelectionModel().getSelectedItem().getDateOfBirth())) {
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });
        professorNameField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(professorList.getSelectionModel().getSelectedItem().getName())) {
                professorNameField.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if(!newIme.isEmpty()) {
                professorNameField.getStyleClass().removeAll("invalid");
            }
            else professorNameField.getStyleClass().addAll("invalid");
        });
        professorSurnameField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(professorList.getSelectionModel().getSelectedItem().getSurname())) {
                professorSurnameField.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if(!newIme.isEmpty()) {
                professorSurnameField.getStyleClass().removeAll("invalid");
            }
            else professorSurnameField.getStyleClass().addAll("invalid");
        });
        professorJmbgField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(professorList.getSelectionModel().getSelectedItem().getJmbg())) {
                professorJmbgField.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }  if(!newIme.isEmpty()) {
                professorJmbgField.getStyleClass().removeAll("invalid");
            }
            else professorJmbgField.getStyleClass().addAll("invalid");
        });
        professorLivingPlaceField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(professorList.getSelectionModel().getSelectedItem().getLivingPlace())) {
                professorLivingPlaceField.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if(!newIme.isEmpty()) {
                professorLivingPlaceField.getStyleClass().removeAll("invalid");
            }
            else professorLivingPlaceField.getStyleClass().addAll("invalid");
        });
        professorBirthPlaceField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()&&!newIme.equals(professorList.getSelectionModel().getSelectedItem().getPlaceOfBirth())) {
                professorBirthPlaceField.getStyleClass().removeAll("invalid");
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if(!newIme.isEmpty()) {
                professorBirthPlaceField.getStyleClass().removeAll("invalid");
            }
            else professorBirthPlaceField.getStyleClass().addAll("invalid");
        });
        professorDatePicker.valueProperty().addListener((obs, oldIme, newIme) -> {
            if (professorDatePicker.getValue()!=null&&!newIme.equals(professorList.getSelectionModel().getSelectedItem().getDateOfBirth())) {
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        userNameField.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.isEmpty()&&!t1.equals(professorList.getSelectionModel().getSelectedItem().getUsername())) {
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(!t1.isEmpty())
            {
                userNameField.getStyleClass().removeAll("invalid");
            }
            else userNameField.getStyleClass().addAll("invalid");
        });
        passwordField.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.isEmpty()&&!t1.equals(professorList.getSelectionModel().getSelectedItem().getPassword())) {
                try {
                    professorList.getSelectionModel().getSelectedItem().setPassword(t1);
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
             if(!t1.isEmpty())
            {
                passwordField.getStyleClass().removeAll("invalid");
            }
            else passwordField.getStyleClass().addAll("invalid");
        });

    }

    public void addStudentAction(ActionEvent actionEvent) throws SQLException {
        //students.add(new Student());
        model.getStudents().add(new Student());
        studentsList.setItems(model.getStudents());
        studentsList.getSelectionModel().selectLast();
        model.addUserBase(studentsList.getSelectionModel().getSelectedItem());
        studentsList.refresh();

    }

    public void deleteStudentAction(ActionEvent actionEvent) {
        if(studentsList.getSelectionModel().getSelectedItem()==null)return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to delete student: "+studentsList.getSelectionModel().getSelectedItem());
        ButtonType buttonType = new ButtonType("Yes");
        ButtonType buttonType1=new ButtonType("No");
        alert.getButtonTypes().setAll(buttonType,buttonType1);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==buttonType) {
            try {
                model.deletePersonBase(studentsList.getSelectionModel().getSelectedItem());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            model.getStudents().removeAll(studentsList.getSelectionModel().getSelectedItem());
            //students.removeAll(studentsList.getSelectionModel().getSelectedItem());
            studentsList.setItems(model.getStudents());
            //userStudentList.setItems(model.getStudents());
            studentsList.refresh();
        }
        else
            alert.close();
    }

    public void detailsAction(ActionEvent actionEvent) throws IOException {
        if(studentsList.getSelectionModel().getSelectedItem() == null) return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/detailsPanel.fxml"));
        DetailsController detailsController =new DetailsController(studentsList.getSelectionModel().getSelectedItem());
        loader.setController(detailsController);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Details");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void addProfessorAction(ActionEvent actionEvent) throws SQLException {
        //professors.add(new Professor());
        model.getProfessors().add(new Professor());
        model.addUserBase(professorList.getSelectionModel().getSelectedItem());
        professorList.getSelectionModel().selectLast();
        //model.getProfessors().add(professorList.getSelectionModel().getSelectedItem());
        professorList.setItems(model.getProfessors());
        professorList.refresh();
    }

    public void deleteProfesorAction(ActionEvent actionEvent) throws SQLException {
        if(professorList.getSelectionModel().getSelectedItem()==null) return;;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to delete professor: "+professorList.getSelectionModel().getSelectedItem());
        ButtonType buttonType = new ButtonType("Yes");
        ButtonType buttonType1=new ButtonType("No");
        alert.getButtonTypes().setAll(buttonType,buttonType1);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==buttonType) {
            model.deletePersonBase(professorList.getSelectionModel().getSelectedItem());
            model.removeByProfessor(professorList.getSelectionModel().getSelectedItem());
            subjects = FXCollections.observableArrayList(model.getSubjects());
            classesTable.setItems(subjects);
            classesTable.refresh();
            model.getProfessors().removeAll(professorList.getSelectionModel().getSelectedItem());
        }
        else
            alert.close();
    }

    public void classesAction(ActionEvent actionEvent) throws IOException {
        if(professorList.getSelectionModel().getSelectedItem() == null) return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/classesPanel.fxml"));
        classesListController classesListController =new classesListController(professorList.getSelectionModel().getSelectedItem());
        loader.setController(classesListController);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Details");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void addClassAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addClassPanel.fxml"));
        addClassController add=new addClassController();
        loader.setController(add);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Details");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
            stage.setOnHiding(event -> {
                Subject subject = add.getSubject();
                if (subject != null) {
                    model.getSubjects().add(subject);
                    classesTable.setItems(model.getSubjects());
                    classesTable.refresh();
                    for (Professor profesor : professors
                            ) {
                        if(profesor.getId() == subject.getProfessor().getId()) {
                            profesor.getSubjects().add(subject);
                        }
                    }

                }
            });


    }

    public void deleteClassAction(ActionEvent actionEvent) throws SQLException {
        if(classesTable.getSelectionModel().selectedItemProperty().getValue()==null)return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to delete class: "+classesTable.getSelectionModel().getSelectedItem());
        ButtonType buttonType = new ButtonType("Yes");
        ButtonType buttonType1=new ButtonType("No");
        alert.getButtonTypes().setAll(buttonType,buttonType1);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==buttonType) {
            for (Professor prof : model.getProfessors()) {
                prof.getSubjects().remove(classesTable.getSelectionModel().selectedItemProperty().get());
            }
            model.deleteSubjectBase(classesTable.getSelectionModel().getSelectedItem());
            model.getSubjects().removeAll(classesTable.getSelectionModel().selectedItemProperty().get());
            classesTable.setItems(model.getSubjects());
            classesTable.refresh();
        }
        else
            alert.close();
    }

    public void addUserAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add user");
        alert.setContentText("Choose type of user");

        ButtonType buttonTypeOne = new ButtonType("Student");
        ButtonType buttonTypeTwo = new ButtonType("Professor");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            adminTabPane.getSelectionModel().select(studentsTab);

        } else if (result.get() == buttonTypeTwo) {
            adminTabPane.getSelectionModel().select(professorsTab);
        } else {
            alert.close();
        }
    }
    public void userAddMouse()
    {
        model.setPerson(userStudentList.getSelectionModel().getSelectedItem());
    }
    public void userProfAddMouse()
    {
        model.setPerson(userProfessorList.getSelectionModel().getSelectedItem());
    }


    public void deleteUserAction(ActionEvent actionEvent) throws SQLException {
        if((userProfessorList.getItems().isEmpty()&&userStudentList.getItems().isEmpty())||(userProfessorList.getSelectionModel().isEmpty()&&userStudentList.getSelectionModel().isEmpty()))
            return;
        if (model.getPerson() instanceof Student) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to delete user: "+userStudentList.getSelectionModel().getSelectedItem());
            ButtonType buttonType = new ButtonType("Yes");
            ButtonType buttonType1=new ButtonType("No");
            alert.getButtonTypes().setAll(buttonType,buttonType1);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()==buttonType) {
                model.deletePersonBase(userStudentList.getSelectionModel().getSelectedItem());
                model.getStudents().removeAll(userStudentList.getSelectionModel().getSelectedItem());
                userStudentList.refresh();
            }
            else
                alert.close();
        }
        else if(model.getPerson() instanceof Professor)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to delete user: " + userProfessorList.getSelectionModel().getSelectedItem());
            ButtonType buttonType = new ButtonType("Yes");
            ButtonType buttonType1=new ButtonType("No");
            alert.getButtonTypes().setAll(buttonType,buttonType1);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()==buttonType) {
                model.removeByProfessor(userProfessorList.getSelectionModel().getSelectedItem());
                subjects = FXCollections.observableArrayList(model.getSubjects());
                classesTable.setItems(subjects);
                classesTable.refresh();
                model.deletePersonBase(userProfessorList.getSelectionModel().getSelectedItem());
                model.getProfessors().removeAll(userProfessorList.getSelectionModel().getSelectedItem());
                userProfessorList.refresh();
            }
            else
                alert.close();
        }

       else {
          return;
        }
    }

}
