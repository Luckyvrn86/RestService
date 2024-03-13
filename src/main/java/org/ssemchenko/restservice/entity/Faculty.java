package org.ssemchenko.restservice.entity;

import java.util.List;
import java.util.Objects;

public class Faculty {
    private int id;
    private String name;
    private List<Student> students;

    public Faculty(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public Faculty() {
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
        Faculty faculty = (Faculty) o;
        return id == faculty.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return  id +
                ". "+ name;
    }
}
