package org.ssemchenko.restservice.model;

import java.util.List;

public class Teacher {
    private int id;
    private String name;
    private Faculty faculty;
    private List<Student> students;

    public Teacher(String name, Faculty faculty, List<Student> students) {
        this.name = name;
        this.faculty = faculty;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
