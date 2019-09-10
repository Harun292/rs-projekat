package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            image=new Image(new FileInputStream("C:\\Users\\ESAD-PC\\IdeaProjects\\E-Index\\src\\sample\\prijava.jpg"));
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




    }
    public void addStudentAction(ActionEvent actionEvent) {
        //students.add(new Student());
        model.getStudents().add(new Student());
        studentsList.setItems(model.getStudents());
        studentsList.getSelectionModel().selectLast();
        studentsList.refresh();

    }

    public void deleteStudentAction(ActionEvent actionEvent) {
        model.getStudents().removeAll(studentsList.getSelectionModel().getSelectedItem());
        //students.removeAll(studentsList.getSelectionModel().getSelectedItem());
        studentsList.setItems(model.getStudents());
        //userStudentList.setItems(model.getStudents());
        studentsList.refresh();
    }

    public void detailsAction(ActionEvent actionEvent) throws IOException {
        if(studentsList.getSelectionModel().getSelectedItem() == null) return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsPanel.fxml"));
        DetailsController detailsController =new DetailsController(studentsList.getSelectionModel().getSelectedItem());
        loader.setController(detailsController);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Details");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addProfessorAction(ActionEvent actionEvent) {
        //professors.add(new Professor());
        model.getProfessors().add(new Professor());
        professorList.getSelectionModel().selectLast();
        professorList.setItems(model.getProfessors());
        //model.getProfessors().add(professorList.getSelectionModel().getSelectedItem());
        professorList.refresh();
    }

    public void deleteProfesorAction(ActionEvent actionEvent) {
        model.removeByProfessor(professorList.getSelectionModel().getSelectedItem());
        subjects=FXCollections.observableArrayList(model.getSubjects());
        classesTable.setItems(subjects);
        classesTable.refresh();
        model.getProfessors().removeAll(professorList.getSelectionModel().getSelectedItem());
    }

    public void classesAction(ActionEvent actionEvent) throws IOException {
        if(professorList.getSelectionModel().getSelectedItem() == null) return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("classesPanel.fxml"));
        classesListController classesListController =new classesListController(professorList.getSelectionModel().getSelectedItem());
        loader.setController(classesListController);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Details");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addClassAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addClassPanel.fxml"));
        addClassController add=new addClassController();
        loader.setController(add);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Details");
        stage.setScene(new Scene(root));
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

        for (Professor prof : model.getProfessors()){
            prof.getSubjects().remove(classesTable.getSelectionModel().selectedItemProperty().get());
        }
        model.getSubjects().removeAll(classesTable.getSelectionModel().selectedItemProperty().get());
        classesTable.setItems(model.getSubjects());
        classesTable.refresh();
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

    public void deleteUserAction(ActionEvent actionEvent) {
        if (userStudentList.getSelectionModel().getSelectedItem()!=null) {
            model.getStudents().removeAll(userStudentList.getSelectionModel().getSelectedItem());
            userStudentList.refresh();
        }

        else {
            model.removeByProfessor(userProfessorList.getSelectionModel().getSelectedItem());
            subjects=FXCollections.observableArrayList(model.getSubjects());
            classesTable.setItems(subjects);
            classesTable.refresh();
            model.getProfessors().removeAll(userProfessorList.getSelectionModel().getSelectedItem());

        }
            userProfessorList.refresh();


    }
}
