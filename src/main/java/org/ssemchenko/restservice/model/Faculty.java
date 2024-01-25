package org.ssemchenko.restservice.model;

import java.util.List;

public class Faculty {
    private int id;
    private String name;
    private List<Student> students;
    private List<Teacher> teachers;

    public Faculty(String name, List<Student> students, List<Teacher> teachers) {
        this.name = name;
        this.students = students;
        this.teachers = teachers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
