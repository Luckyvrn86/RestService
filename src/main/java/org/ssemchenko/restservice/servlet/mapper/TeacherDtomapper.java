package org.ssemchenko.restservice.servlet.mapper;

import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;
import org.ssemchenko.restservice.servlet.dto.StudentDto;

public interface TeacherDtomapper {
    Teacher map(FacultyDto facultyDto);
    StudentDto map(Teacher teacher);
}
