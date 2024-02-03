package org.ssemchenko.restservice.repository.mapper.impl;

import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.repository.mapper.StudentResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentResultSetMapperImpl implements StudentResultSetMapper {
    @Override
    public Student map(ResultSet resultSet) {
        Student student = new Student();
        try {
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setFacultyId(resultSet.getInt("faculty"));
            student.setTeachers(new ArrayList<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
}
