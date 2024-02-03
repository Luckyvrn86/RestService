package org.ssemchenko.restservice.repository.mapper.impl;

import org.ssemchenko.restservice.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapperFromTeacher {

    public Student map(ResultSet resultSet) {
        Student student = new Student();
        try {
            student.setId(resultSet.getInt("student_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
}
