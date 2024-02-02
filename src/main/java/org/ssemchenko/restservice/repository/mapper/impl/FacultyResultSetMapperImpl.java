package org.ssemchenko.restservice.repository.mapper.impl;

import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.repository.mapper.FacultyResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FacultyResultSetMapperImpl implements FacultyResultSetMapper {
    @Override
    public Faculty map(ResultSet resultSet) {
        Faculty faculty = new Faculty();
        try {
            faculty.setId(resultSet.getInt("id"));
            faculty.setName(resultSet.getString("name"));
            faculty.setStudents(new ArrayList<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return faculty;
    }
}
