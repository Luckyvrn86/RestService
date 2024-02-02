package org.ssemchenko.restservice.repository.mapper;

import org.mapstruct.Mapper;
import org.ssemchenko.restservice.model.Faculty;

import java.sql.ResultSet;

public interface FacultyResultSetMapper {

    Faculty map(ResultSet resultSet);

}
