package org.ssemchenko.restservice.servlet.mapper;

import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;
import org.ssemchenko.restservice.servlet.dto.StudentDto;

public interface StudentDtomapper {
    Student map(FacultyDto facultyDto);
    StudentDto map(Student student);
}
