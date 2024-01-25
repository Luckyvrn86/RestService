package org.ssemchenko.restservice.model;

import java.util.List;

public class Student {
    private int id;
    private String name;
    private Faculty faculty;
    private List<Teacher> teachers;

    public Student(String name, Faculty faculty, List<Teacher> teachers) {
        this.name = name;
        this.faculty = faculty;
        this.teachers = teachers;
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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
