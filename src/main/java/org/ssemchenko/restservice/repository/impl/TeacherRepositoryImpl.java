package org.ssemchenko.restservice.repository.impl;

import org.ssemchenko.restservice.db.ConnectionManager;
import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.repository.TeacherRepository;
import org.ssemchenko.restservice.repository.mapper.TeacherResultSetMapper;

import java.util.List;

public class TeacherRepositoryImpl implements TeacherRepository {
    private ConnectionManager connectionManager;
    private TeacherResultSetMapper teacherResultSetMapper;
    @Override
    public Teacher findById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public List<Teacher> findAll() {
        return null;
    }

    @Override
    public Teacher save(Teacher teacher) {
        return null;
    }
}
