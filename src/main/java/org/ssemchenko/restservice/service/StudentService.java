package org.ssemchenko.restservice.service;

import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;
import org.ssemchenko.restservice.servlet.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto save(Student student);
    List<StudentDto> findAll();
    boolean deleteById(int id);
    StudentDto findById(int id);
    List<StudentDto> findByFacultyId(int facultyId);
}
