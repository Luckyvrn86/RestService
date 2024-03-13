package org.ssemchenko.restservice.service;

import org.ssemchenko.restservice.service.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    TeacherDto save(TeacherDto teacher);
    TeacherDto findById(int id);
    void deleteById(int id);
    List<TeacherDto> findAll();

}
