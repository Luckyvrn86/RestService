package org.ssemchenko.restservice.service.dto;

import java.util.Objects;

public class TeacherDto {
    private int id;
    private String name;

    public TeacherDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TeacherDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDto that = (TeacherDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}
