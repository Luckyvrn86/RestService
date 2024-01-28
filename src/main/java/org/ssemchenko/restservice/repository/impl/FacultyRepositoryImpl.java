package org.ssemchenko.restservice.repository.impl;

import org.ssemchenko.restservice.db.ConnectionManager;
import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.repository.FacultyRepository;
import org.ssemchenko.restservice.repository.mapper.FacultyResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class FacultyRepositoryImpl implements FacultyRepository {
    public static final String FIND_BY_ID = """
            select *
            from faculty
            where id = ?
            """;

    private ConnectionManager connectionManager;
    private FacultyResultSetMapper resultMapper;
    @Override
    public Optional<Faculty> findById(Integer id) {
        Optional<Faculty> result;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            result = resultMapper.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public Optional<Faculty> findAll() {
        return null;
    }

    @Override
    public Optional<Faculty> save(Optional<Faculty> faculty) {
        return Optional.empty();
    }
}
