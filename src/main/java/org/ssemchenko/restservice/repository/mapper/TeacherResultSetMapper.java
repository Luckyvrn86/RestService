package org.ssemchenko.restservice.repository.mapper;

import org.ssemchenko.restservice.model.Teacher;

import java.sql.ResultSet;

public interface TeacherResultSetMapper {

    Teacher map(ResultSet resultSet);
}
