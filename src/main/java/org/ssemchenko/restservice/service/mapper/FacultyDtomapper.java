package org.ssemchenko.restservice.service.mapper;

import org.mapstruct.Mapper;
import org.ssemchenko.restservice.entity.Faculty;
import org.ssemchenko.restservice.service.dto.FacultyDto;

@Mapper
public interface FacultyDtomapper {
    Faculty map(FacultyDto facultyDto);
    FacultyDto map(Faculty faculty);
}
