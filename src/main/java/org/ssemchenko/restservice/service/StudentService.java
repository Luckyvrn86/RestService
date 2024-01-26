package org.ssemchenko.restservice.service;

import org.ssemchenko.restservice.model.Student;

public interface StudentService {
    Student save(Student student);
    Student findById(int id);
}
