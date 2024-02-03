package org.ssemchenko.restservice.repository.mapper.impl;

import org.ssemchenko.restservice.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapperFromStudent {
    public Teacher map(ResultSet resultSet){
        Teacher teacher = new Teacher();
        try {
            teacher.setId(resultSet.getInt("teacher_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teacher;
    }
}
