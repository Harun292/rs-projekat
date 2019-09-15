import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import sample.*;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BaseTest {
    Model model=Model.getInstance();
    @Test
    void getUsersTest() throws SQLException {
        model.getPersons();
        ObservableList<Person> persons=model.getUsers();
        assertFalse(persons.get(0) instanceof Professor);
        assertEquals(3,model.getUsers().size());
        assertEquals("Harun",persons.get(0).getName());
        model.setUsers(FXCollections.observableArrayList());
        model.setStudents(FXCollections.observableArrayList());
        model.setProfessors(FXCollections.observableArrayList());
    }
    @Test
    void getGradesTest() throws SQLException {
        ArrayList<Grades> grades=model.getGrades();
        assertEquals(4,model.getGrades().size());
        assertEquals(1,model.getGrades().get(0).getId());
    }
    @Test
        void getSubjectsTest() throws SQLException {
        model.getSubjectsBase();
        assertEquals(3,model.getSubjects().size());
        assertTrue(1==model.getSubjects().get(0).getId());
    }
   @Test
        void checkUsernameTest() throws SQLException {
       model.getPersons();
       ObservableList<Person> persons=model.getUsers();
       assertEquals("hajkunic1",persons.get(0).getUsername());
       model.setUsers(FXCollections.observableArrayList());
       model.setStudents(FXCollections.observableArrayList());
       model.setProfessors(FXCollections.observableArrayList());
   }
    @Test
        void checkIndexTest() throws SQLException {
        model.getPersons();
        ObservableList<Student> persons=model.getStudents();
        assertEquals(13212,persons.get(0).getIndex());
        model.setUsers(FXCollections.observableArrayList());
        model.setStudents(FXCollections.observableArrayList());
        model.setProfessors(FXCollections.observableArrayList());
    }
    @Test
        void deleteStudentTest() throws SQLException {
        Student student=new Student();
        model.addUserBase(student);
        model.getPersons();
        assertEquals(3,model.getStudents().size());
        assertEquals(4,model.getUsers().size());
        model.setUsers(FXCollections.observableArrayList());
        model.setStudents(FXCollections.observableArrayList());
        model.setProfessors(FXCollections.observableArrayList());
        model.deletePersonBase(student);
    }
    @Test
    void deleteProfesorTest() throws SQLException {
        Professor professor=new Professor();
        model.addUserBase(professor);
        model.getPersons();
        assertEquals(2,model.getProfessors().size());
        assertEquals(4,model.getUsers().size());
        model.setUsers(FXCollections.observableArrayList());
        model.setStudents(FXCollections.observableArrayList());
        model.setProfessors(FXCollections.observableArrayList());
        model.deletePersonBase(professor);
        model.getPersons();
        assertEquals(1,model.getProfessors().size());
        assertEquals(3,model.getUsers().size());
        model.setUsers(FXCollections.observableArrayList());
        model.setStudents(FXCollections.observableArrayList());
        model.setProfessors(FXCollections.observableArrayList());
    }


}
