package org.ssemchenko.restservice.repository.mapper;

import org.mapstruct.Mapper;
import org.ssemchenko.restservice.model.Faculty;

import java.sql.ResultSet;
import java.util.Optional;

@Mapper
public interface FacultyResultSetMapper {

    Optional<Faculty> map(ResultSet resultSet);

}
