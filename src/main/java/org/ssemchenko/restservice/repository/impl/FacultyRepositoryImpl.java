package org.ssemchenko.restservice.repository.impl;

import org.ssemchenko.restservice.db.ConnectionManager;
import org.ssemchenko.restservice.db.ConnectionManagerImpl;
import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.repository.FacultyRepository;
import org.ssemchenko.restservice.repository.mapper.FacultyResultSetMapper;
import org.ssemchenko.restservice.util.SqlString;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FacultyRepositoryImpl implements FacultyRepository {
    public static final FacultyRepositoryImpl INSTANCE = new FacultyRepositoryImpl();
    private ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
    private FacultyResultSetMapper resultMapper;
    private String name = "university.faculty";

    private FacultyRepositoryImpl() {
    }

    @Override
    public Faculty findById(Integer id) {
        Faculty result;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(SqlString.FIND_BY_ID)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            var resultSet = preparedStatement.executeQuery();
            result = resultMapper.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(SqlString.DELETE_BY_ID)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Faculty> findAll() {
        List<Faculty> result = new ArrayList<>();
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(SqlString.FIND_ALL)) {
            preparedStatement.setString(1, name);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(resultMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Faculty save(Faculty faculty) {
        String sql = "insert into " + name + " (name) values (" + faculty.getName();
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) faculty.setId(generatedKeys.getInt("id"));
            return faculty;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static FacultyRepositoryImpl getInstance(){
        return INSTANCE;
    }
}
