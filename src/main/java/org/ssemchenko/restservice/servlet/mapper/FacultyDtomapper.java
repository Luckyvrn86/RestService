package org.ssemchenko.restservice.servlet.mapper;

import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;
import org.ssemchenko.restservice.servlet.dto.StudentDto;

public interface FacultyDtomapper {
    Faculty map(FacultyDto facultyDto);
    FacultyDto map(Faculty faculty);
}
