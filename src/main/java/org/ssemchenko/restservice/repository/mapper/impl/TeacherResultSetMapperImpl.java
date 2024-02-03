package org.ssemchenko.restservice.repository.mapper.impl;

import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.repository.mapper.TeacherResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherResultSetMapperImpl implements TeacherResultSetMapper {
    @Override
    public Teacher map(ResultSet resultSet) {
        Teacher teacher = new Teacher();
        try {
            teacher.setId(resultSet.getInt("id"));
            teacher.setName(resultSet.getString("name"));
            teacher.setStudents(new ArrayList<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teacher;
    }
}
