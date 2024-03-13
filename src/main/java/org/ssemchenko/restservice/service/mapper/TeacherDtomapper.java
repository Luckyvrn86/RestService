package org.ssemchenko.restservice.service.mapper;

import org.mapstruct.Mapper;
import org.ssemchenko.restservice.entity.Teacher;
import org.ssemchenko.restservice.service.dto.TeacherDto;

@Mapper
public interface TeacherDtomapper {
    Teacher map(TeacherDto teacherDto);
    TeacherDto map(Teacher teacher);
}
