package org.ssemchenko.restservice.service;

import org.ssemchenko.restservice.service.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto save(StudentDto student);
    List<StudentDto> findAll();
    void deleteById(int id);
    StudentDto findById(int id);
}
