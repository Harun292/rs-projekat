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
            image=new Image(new FileInputStream("C:\\Users\\ESAD-PC\\IdeaProjects\\E-Index\\resources\\images\\prijava.jpg"));
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
            if (!newIme.isEmpty()) {
                nameField.getStyleClass().removeAll("invalid");
                studentsList.getSelectionModel().getSelectedItem().setName(newIme);
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                nameField.getStyleClass().add("invalid");
            }
        });
        surnameField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                surnameField.getStyleClass().removeAll("invalid");
                studentsList.getSelectionModel().getSelectedItem().setSurname(newIme);
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                surnameField.getStyleClass().add("invalid");
            }
        });
        indexField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                indexField.getStyleClass().removeAll("invalid");
                studentsList.getSelectionModel().getSelectedItem().setIndex(Integer.parseInt(newIme));
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                indexField.getStyleClass().add("invalid");
            }
        });

        userNameFieldStud.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                userNameFieldStud.getStyleClass().removeAll("invalid");
                studentsList.getSelectionModel().getSelectedItem().setUsername(newIme);
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                userNameFieldStud.getStyleClass().add("invalid");
            }
        });


        passwordFieldStud.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                passwordFieldStud.getStyleClass().removeAll("invalid");
                studentsList.getSelectionModel().getSelectedItem().setPassword(newIme);
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                passwordFieldStud.getStyleClass().add("invalid");
            }
        });

        jmbgField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                jmbgField.getStyleClass().removeAll("invalid");
                studentsList.getSelectionModel().getSelectedItem().setJmbg(newIme);
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                jmbgField.getStyleClass().add("invalid");
            }
        });
        livingPlaceField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                livingPlaceField.getStyleClass().removeAll("invalid");
                studentsList.getSelectionModel().getSelectedItem().setLivingPlace(newIme);
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                livingPlaceField.getStyleClass().add("invalid");
            }
        });
        birthPlaceField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                birthPlaceField.getStyleClass().removeAll("invalid");
                studentsList.getSelectionModel().getSelectedItem().setPlaceOfBirth(newIme);
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                birthPlaceField.getStyleClass().add("invalid");
            }
        });

        motherField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                motherField.getStyleClass().removeAll("invalid");
                studentsList.getSelectionModel().getSelectedItem().setMothersName(newIme);
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                motherField.getStyleClass().addAll("invalid");
            }
        });

        fatherField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fatherField.getStyleClass().removeAll("invalid");
                studentsList.getSelectionModel().getSelectedItem().setFathersName(newIme);
                try {
                    model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                fatherField.getStyleClass().addAll("invalid");
            }
        });
        datePicker.valueProperty().addListener((observableValue, localDate, t1) -> {
            if(datePicker.getValue()!=null) {
                studentsList.getSelectionModel().getSelectedItem().setDateOfBirth(t1);
                datePicker.getStyleClass().removeAll("invalid");
            }
            else
            {
                datePicker.getStyleClass().add("invalid");
            }
            try {
                model.updateUserBase(studentsList.getSelectionModel().getSelectedItem());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        professorNameField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                professorNameField.getStyleClass().removeAll("invalid");
                professorList.getSelectionModel().getSelectedItem().setName(newIme);
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                professorNameField.getStyleClass().add("invalid");
            }
        });
        professorSurnameField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                professorSurnameField.getStyleClass().removeAll("invalid");
                professorList.getSelectionModel().getSelectedItem().setSurname(newIme);
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                professorSurnameField.getStyleClass().add("invalid");
            }
        });
        professorBirthPlaceField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                professorBirthPlaceField.getStyleClass().removeAll("invalid");
                professorList.getSelectionModel().getSelectedItem().setPlaceOfBirth(newIme);
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                professorBirthPlaceField.getStyleClass().add("invalid");
            }
        });
        professorLivingPlaceField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                professorLivingPlaceField.getStyleClass().removeAll("invalid");
                professorList.getSelectionModel().getSelectedItem().setLivingPlace(newIme);
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                professorLivingPlaceField.getStyleClass().add("invalid");
            }
        });
        professorJmbgField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                professorJmbgField.getStyleClass().removeAll("invalid");
                professorList.getSelectionModel().getSelectedItem().setJmbg(newIme);
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                professorJmbgField.getStyleClass().add("invalid");
            }
        });
        userNameField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                userNameField.getStyleClass().removeAll("invalid");
                professorList.getSelectionModel().getSelectedItem().setUsername(newIme);
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                userNameField.getStyleClass().add("invalid");
            }
        });
        passwordField.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                passwordField.getStyleClass().removeAll("invalid");
                professorList.getSelectionModel().getSelectedItem().setPassword(newIme);
                try {
                    model.updateUserBase(professorList.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                passwordField.getStyleClass().add("invalid");
            }
        });
       /* userStudentList.getSelectionModel().selectedItemProperty().addListener((observableValue, student, t1) -> {
            if(t1 != null){
                model.setPerson(t1);
                System.out.println(model.getPerson().toString());
                userStudentList.refresh();
                userProfessorList.refresh();
            }
        });

        userProfessorList.getSelectionModel().selectedItemProperty().addListener((observableValue, professor, t1) -> {

            if(t1 != null){
                System.out.println(model.getPerson().toString());
                model.setPerson(t1);
                userStudentList.refresh();
                userProfessorList.refresh();
            }
        });
*/
    }
    public void addStudentAction(ActionEvent actionEvent) throws SQLException {
        //students.add(new Student());
        model.getStudents().add(new Student());
        studentsList.setItems(model.getStudents());
        studentsList.getSelectionModel().selectLast();
        studentsList.refresh();
        model.addUserBase(studentsList.getSelectionModel().getSelectedItem());

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
        professorList.getSelectionModel().selectLast();
        professorList.setItems(model.getProfessors());
        //model.getProfessors().add(professorList.getSelectionModel().getSelectedItem());
        professorList.refresh();
        model.addUserBase(professorList.getSelectionModel().getSelectedItem());
    }

    public void deleteProfesorAction(ActionEvent actionEvent) {
        if(professorList.getSelectionModel().getSelectedItem()==null) return;;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to delete professor: "+professorList.getSelectionModel().getSelectedItem());
        ButtonType buttonType = new ButtonType("Yes");
        ButtonType buttonType1=new ButtonType("No");
        alert.getButtonTypes().setAll(buttonType,buttonType1);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==buttonType) {
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

    public void deleteClassAction(ActionEvent actionEvent) {
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


    public void deleteUserAction(ActionEvent actionEvent) {
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
