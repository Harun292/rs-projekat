import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import sample.*;

import javax.swing.text.html.ListView;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    @Test
        void setNameTest()
    {
        Person person=new Person();
        person.setName("Edna");
        assertEquals("Edna",person.getName());
    }
    @Test
        void constructorTest()
    {
        Person person=new Person("Edis","Kraljević","3232","Bugojno","Sarajevo", LocalDate.now(),1,"edis","123456");
        assertEquals("3232",person.getJmbg());
        assertEquals(LocalDate.now(),person.getDateOfBirth());
        assertEquals(1,person.getId());
        assertEquals("123456",person.getPassword());
    }
    @Test
        void getterSetterTest()
    {
        Student student=new Student();
        student.setFathersName("Esad");
        assertEquals("Esad",student.getFathersName());
        student.setDateOfBirth(LocalDate.now());
        assertEquals(LocalDate.now(),student.getDateOfBirth());
    }
    @Test
        void subjectsTest()
    {
        Professor professor=new Professor();
        ArrayList<Subject> subjects=new ArrayList<>();
        for(int i =0;i<5;i++)
        {
            Subject subject = new Subject();
            subject.setId(i);
            subjects.add(subject);
        }
        professor.setSubjects(FXCollections.observableArrayList(subjects));
        assertEquals(0,professor.getSubjects().get(0).getId());
        assertEquals(5,professor.getSubjects().size());
    }

}
