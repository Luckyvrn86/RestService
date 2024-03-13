package org.ssemchenko.restservice.service.mapper;

import org.mapstruct.Mapper;
import org.ssemchenko.restservice.entity.Student;
import org.ssemchenko.restservice.service.dto.StudentDto;
@Mapper
public interface StudentDtomapper {
    Student map(StudentDto studentDto);
    StudentDto map(Student student);
}
