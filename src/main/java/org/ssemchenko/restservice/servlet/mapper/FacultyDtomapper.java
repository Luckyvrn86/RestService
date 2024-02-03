package org.ssemchenko.restservice.servlet.mapper;

import org.mapstruct.Mapper;
import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;

@Mapper
public interface FacultyDtomapper {
    Faculty map(FacultyDto facultyDto);
    FacultyDto map(Faculty faculty);
}
