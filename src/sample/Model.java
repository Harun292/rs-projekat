package sample;

import java.time.LocalDate;
import java.util.ArrayList;

public class Model {
    ArrayList<Person> users=new ArrayList<>();
    Student person=new Student();
    ArrayList<Subject> subjects=new ArrayList<>();

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setUsers(ArrayList<Person> users) {
        this.users = users;
    }

    public Student getPerson() {
        return person;
    }

    public void setPerson(Student student) {
        this.person = student;
    }

    public ArrayList<Person> getUsers() {
        return users;
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
        users.add(student);
        Professor professor=new Professor();
        professor.setSurname("Ajkunić");
        professor.setName("admin");
        professor.setDateOfBirth(LocalDate.now());
        professor.setJmbg("43353453");
        professor.setLivingPlace("Bugojno");professor.setPlaceOfBirth("Travnik");
        users.add(professor);


        Subject tp=new Subject();
        tp.setId(1);
        tp.setProfessor((Professor) users.get(1));
        tp.setSubjectName("Tehnike programiranja");
        Subject ram=new Subject();
        ram.setId(4);
        ram.setProfessor((Professor) users.get(1));
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
        this.setPerson(student);
    }



}
