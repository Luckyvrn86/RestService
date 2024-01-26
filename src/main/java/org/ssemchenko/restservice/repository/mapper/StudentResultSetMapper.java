package org.ssemchenko.restservice.repository.mapper;

import org.ssemchenko.restservice.model.Student;

import java.sql.ResultSet;

public interface StudentResultSetMapper {

    Student map(ResultSet resultSet);
}
