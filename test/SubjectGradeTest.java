import org.junit.jupiter.api.Test;
import sample.Grades;
import sample.Professor;
import sample.Subject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SubjectGradeTest {

    @Test
    void setNameTest()
    {
        Subject subject=new Subject();
        Professor professor=new Professor();
        professor.setName("Prof");
        subject.setSubjectName("RAM");
        subject.setProfessor(professor);
        subject.setId(1);
        assertEquals("Prof",subject.getProfessor().getName());
        assertEquals(1,subject.getId());
        assertFalse(subject.getSubjectName().equals("MAR"));
    }
    @Test
        void setGradeTest()
    {
        Grades grade=new Grades();
        Professor professor=new Professor();
        professor.setName("Harun");
        Subject subject=new Subject("Razvoj softvera",1,professor);
        grade.setNumberOfPoints(22);
        grade.setSubject(subject);
        grade.setGrade(22);
        assertEquals("Harun",grade.getSubject().getProfessor().getName());
        assertEquals(22,grade.getNumberOfPoints());
        assertFalse(grade.getGrade()==32);
    }
    @Test
        void subjectConstructorTest()
    {
        Subject subject=new Subject("Ram",2,null);
        assertFalse(subject.getProfessor()!=null);
        assertEquals("Ram",subject.getSubjectName());
        subject.setSubjectName("Razvoj softvera");
        assertEquals("Razvoj softvera",subject.getSubjectName());
    }

}
