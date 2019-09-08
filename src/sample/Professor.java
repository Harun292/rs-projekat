package sample;

import java.util.ArrayList;

public class Professor extends Person {
    ArrayList<Subject> subjects=new ArrayList<>();

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
}
