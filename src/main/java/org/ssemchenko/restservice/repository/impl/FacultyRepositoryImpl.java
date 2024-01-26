package org.ssemchenko.restservice.repository.impl;

import org.ssemchenko.restservice.db.ConnectionManager;
import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.repository.FacultyRepository;
import org.ssemchenko.restservice.repository.mapper.FacultyResultSetMapper;

public class FacultyRepositoryImpl implements FacultyRepository {
    private ConnectionManager connectionManager;
    private FacultyResultSetMapper resultMapper;
    @Override
    public Faculty findById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public Faculty findAll() {
        return null;
    }

    @Override
    public Faculty save(Faculty faculty) {
        return null;
    }
}
