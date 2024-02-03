package org.ssemchenko.restservice.service;

import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.servlet.dto.StudentDto;
import org.ssemchenko.restservice.servlet.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    Teacher save(Teacher teacher);
    Teacher findById(int id);
    boolean deleteById(int id);
    List<TeacherDto> findAll();

}
