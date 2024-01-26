package org.ssemchenko.restservice.repository.impl;

import org.ssemchenko.restservice.db.ConnectionManager;
import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.repository.StudentRepository;
import org.ssemchenko.restservice.repository.mapper.StudentResultSetMapper;

public class StudentRepositoryImpl implements StudentRepository {
    private ConnectionManager connectionManager;
    private StudentResultSetMapper studentResultSetMapper;
    @Override
    public Student findById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public Student findAll() {
        return null;
    }

    @Override
    public Student save(Student student) {
        return null;
    }
}
