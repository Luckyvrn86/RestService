package org.ssemchenko.restservice.servlet.mapper;

import org.mapstruct.Mapper;
import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;
import org.ssemchenko.restservice.servlet.dto.StudentDto;
import org.ssemchenko.restservice.servlet.dto.TeacherDto;

@Mapper
public interface TeacherDtomapper {
    Teacher map(TeacherDto teacherDto);
    TeacherDto map(Teacher teacher);
}
