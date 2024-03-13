package org.ssemchenko.restservice.entity;

import java.util.List;
import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private int facultyId;
    private List<Teacher> teachers;

    public Student() {
    }

    public Student(String name, int facultyId) {
        this.name = name;
        this.facultyId = facultyId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
