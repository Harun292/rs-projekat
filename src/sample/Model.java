package sample;

import java.time.LocalDate;
import java.util.ArrayList;

public class Model {
    ArrayList<Person> users=new ArrayList<>();
    Person person=new Person();
    void load()
    {
        Student student=new Student();
        student.setName("Harun");
        student.setFathersName("Esad");
        student.setSurname("Ajkunić");
        student.setIndex(12345);
        student.setMothersName("Zafria");
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
    }

    public ArrayList<Person> getUsers() {
        return users;
    }


}
