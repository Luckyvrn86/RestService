package org.ssemchenko.restservice.servlet.mapper;

import org.mapstruct.Mapper;
import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;
import org.ssemchenko.restservice.servlet.dto.StudentDto;
@Mapper
public interface StudentDtomapper {
    Student map(StudentDto studentDto);
    StudentDto map(Student student);
}
