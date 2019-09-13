package sample;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {
    private static Model instance=null;

    ObservableList<Person> users= FXCollections.observableArrayList();
    ObservableList<Professor> professors=FXCollections.observableArrayList();
    ObservableList<Student> students=FXCollections.observableArrayList();
    ObjectProperty<Person> person=new SimpleObjectProperty<>();
    ObservableList<Subject> subjects=FXCollections.observableArrayList();
    Connection connection;
    private PreparedStatement getUsersStmnt,getStudentsStmnt,getSubjectsStmnt,getGradesStmnt,getProfessorStmnt,getSubjectStmnt;

    private Model() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:base.db");
            try {
                getUsersStmnt = connection.prepareStatement("SELECT * FROM USERS");
            } catch (SQLException e) {
                regenerateDatabase();
                try {
                    getUsersStmnt = connection.prepareStatement("SELECT * FROM USERS");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            getStudentsStmnt=connection.prepareStatement("SELECT*FROM STUDENTS ");
            getSubjectsStmnt=connection.prepareStatement("SELECT * FROM SUBJECTS");
            getGradesStmnt=connection.prepareStatement("SELECT * FROM GRADES");
            getProfessorStmnt=connection.prepareStatement("SELECT * FROM USERS WHERE id=?");
            getSubjectStmnt=connection.prepareStatement("SELECT * FROM SUBJECTS WHERE ID=?");
           } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void regenerateDatabase() {
        Scanner input;
        try {
            input = new Scanner(new FileInputStream("base.sql"));
            StringBuilder sqlStatement = new StringBuilder();
            while (input.hasNext()) {
                sqlStatement.append(input.nextLine());
                if (sqlStatement.charAt(sqlStatement.length() - 1) == ';') {
//                    System.out.println("Executing statement: " + sqlStatement);
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(sqlStatement.toString());
                        sqlStatement = new StringBuilder();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Professor getProfessorFromResultSet(ResultSet rs) throws SQLException
    {
        return new Professor(rs.getString(2),rs.getString(3),rs.getString(7),rs.getString(5),rs.getString(6),rs.getDate(4).toLocalDate(),rs.getInt(1),rs.getString(8),rs.getString(9));
    }
    private Student getStudentFromResultSet(ResultSet rs,ResultSet rs1) throws SQLException
    {
        return new Student(rs.getString(2),rs.getString(3),rs.getString(7),rs.getString(5),rs.getString(6),rs.getDate(4).toLocalDate(),rs1.getString(5),rs1.getString(6),rs1.getInt(3),rs.getInt(1),rs.getString(8),rs.getString(9));
    }
    private Subject getSubjectFromResultSet(ResultSet rs) throws SQLException {
        return new Subject(rs.getString(2),rs.getInt(1),null);
    }

    public void getPersons() throws SQLException {
        ResultSet rs=getUsersStmnt.executeQuery();
        ArrayList<Person> userResult=new ArrayList<>();
        while(rs.next())
        {
            if(rs.getInt(10)==1)
            {
                    ResultSet rs1=getStudentsStmnt.executeQuery();
                    Student stud = getStudentFromResultSet(rs,rs1);
                    students.add(stud);
                    users.add(stud);

            }
            else {
                Professor prof = getProfessorFromResultSet(rs);
                users.add(prof);
                professors.add(prof);
            }
        }
    }
    public void getSubjectsBase() throws SQLException
    {
        ResultSet rs=getSubjectsStmnt.executeQuery();
        while(rs.next())
        {
            getProfessorStmnt.setInt(1,rs.getInt(3));
            ResultSet rs1=getProfessorStmnt.executeQuery();
            Professor subjProf=getProfessorFromResultSet(rs1);
            Subject subject=getSubjectFromResultSet(rs);
            subject.setProfessor(subjProf);
            subjects.add(subject);
        }
    }
    public ArrayList<Grades> getGrades() throws SQLException {
        ResultSet rs=getGradesStmnt.executeQuery();
        ArrayList<Grades> grades=new ArrayList<>();
        while(rs.next())
        {
            getSubjectStmnt.setInt(1,rs.getInt(2));
            ResultSet rs1=getSubjectStmnt.executeQuery();
            Subject sub=getSubjectFromResultSet(rs1);
            Grades grade=new Grades();
            grade.setSubject(sub);
            grade.setGrade(rs.getInt(4));
            grade.setNumberOfPoints(rs.getInt(5));
            grade.setId(rs.getInt(3));
            grades.add(grade);
        }
        return grades;
    }


    public Student getById(int i)
    {
        for (Student student :students) {
            System.out.println(student.getId()+" "+i);
            if(student.getId()==i)
                return student;
        }
        return null;
    }

    public ObservableList<Professor> getProfessors() {
        return professors;
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public void setProfessors(ObservableList<Professor> professors) {
        this.professors = professors;
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void setStudents(ObservableList<Student> students) {
        this.students = students;
    }

    public ObservableList<Person> getUsers() {
        return users;
    }

    public void setUsers(ObservableList<Person> users) {
        this.users = users;
    }

    public Person getPerson() {
        return person.get();
    }

    public ObjectProperty<Person> personProperty() {
        return person;
    }

    public void setPerson(Person person) {
        this.person.set(person);
    }

    public ObservableList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ObservableList<Subject> subjects) {
        this.subjects = subjects;
    }

    /*public void load()
    {
        Student student=new Student();
        student.setName("Harun");
        student.setFathersName("Esad");
        student.setSurname("Ajkunić");
        student.setIndex(12345);
        student.setMothersName("Zafira");
        student.setDateOfBirth(LocalDate.now());
        student.setJmbg("43353453");
        student.setLivingPlace("Bugojno");
        student.setPlaceOfBirth("Travnik");
        student.setPassword("123456");
        student.setUsername("hajkunic1");

        Student student1=new Student();
        student1.setName("Harun");
        student1.setFathersName("Esad");
        student1.setSurname("Ajkunić");
        student1.setIndex(12345);
        student1.setMothersName("Zafira");
        student1.setDateOfBirth(LocalDate.now());
        student1.setJmbg("43353453");
        student1.setLivingPlace("Bugojno");
        student1.setPlaceOfBirth("Travnik");
        student1.setPassword("123456");
        student1.setUsername("hajkunic1");

        users.add(student1);
        users.add(student);
        Professor professor=new Professor();
        professor.setId(44);
        professor.setSurname("Ajkunić");
        professor.setName("admin");
        professor.setDateOfBirth(LocalDate.now());
        professor.setJmbg("43353453");
        professor.setLivingPlace("Bugojno");professor.setPlaceOfBirth("Travnik");
        professor.setPassword("123456");
        professor.setUsername("admin");
        users.add(professor);
        Professor professor1 = new Professor();
        professor1.setId(111);
        professor1.setName("Mirza");
        professor1.setSurname("Gojak");
        professor1.setDateOfBirth(LocalDate.now());
        professor.setPassword("123456");
        professor.setUsername("admin");
        users.add(professor1);
        Subject tp=new Subject();
        tp.setId(1);
        tp.setProfessor(professor);
        tp.setSubjectName("Tehnike programiranja");
        Subject ram=new Subject();
        ram.setId(4);
        ram.setProfessor(professor);
        ram.setSubjectName("Račuraske arhitekture i mreže");
        subjects.add(tp);
        subjects.add(ram);

        Grades grade=new Grades();
        grade.setGrade(9);
        grade.setId(1);
        grade.setNumberOfPoints(85);
        Grades grades=new Grades();
        grades.setNumberOfPoints(76);
        grades.setGrade(8);
        grades.setId(2);
        grade.setSubject(tp);
        grades.setSubject(ram);
        student.getGrades().add(grade);
        student.getGrades().add(grades);
        student1.getGrades().add(grade);
        student1.getGrades().add(grades);
        this.setPerson(student);
        professor.getSubjects().add(tp);
        professor.getSubjects().add(ram);

        for (Person person:users) {
            if(person instanceof Professor)
                professors.add((Professor)person);
            else
                students.add((Student) person);
        }
    }*/
    public void removePerson(Person person)
    {
        users.removeAll(person);
    }

    public void removeByProfessor(Professor professor) {
        for (int i = 0; i < subjects.size(); i++) {
            if(subjects.get(i).getProfessor().getId() == professor.getId()) {
                subjects.remove(subjects.get(i));
                i--;
            }
        }
    }
    public Person findByUsername(String username)
    {
        for (Person per:users) {
            if(per.getUsername().equals(username))
                return per;
        }
        return null;
    }

}
