package sample;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class Model {
    private static Model instance=null;

    ObservableList<Person> users= FXCollections.observableArrayList();
    ObservableList<Professor> professors=FXCollections.observableArrayList();
    ObservableList<Student> students=FXCollections.observableArrayList();
    ObjectProperty<Person> person=new SimpleObjectProperty<>();
    ObservableList<Subject> subjects=FXCollections.observableArrayList();

    private Model() {
    }

    public Student getById(int i)
    {
        for (Student student :students) {
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
            instance.load();
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

    public void load()
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
    }
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
            System.out.println(username);
            if(per.getUsername().equals(username))
                return per;
        }
        return null;
    }

}
