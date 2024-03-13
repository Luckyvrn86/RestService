package org.ssemchenko.restservice.entity;

import java.util.List;
import java.util.Objects;

public class Teacher {
    private int id;
    private String name;
    private List<Student> students;

    public Teacher() {
    }

    public Teacher(String name, List<Student> students) {
        this.name = name;
        this.students = students;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
