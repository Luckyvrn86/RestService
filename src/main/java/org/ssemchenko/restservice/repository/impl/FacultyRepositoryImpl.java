package org.ssemchenko.restservice.repository.impl;

import org.ssemchenko.restservice.db.ConnectionManagerImpl;
import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.repository.FacultyRepository;
import org.ssemchenko.restservice.repository.mapper.FacultyResultSetMapper;
import org.ssemchenko.restservice.repository.mapper.StudentResultSetMapper;
import org.ssemchenko.restservice.repository.mapper.impl.FacultyResultSetMapperImpl;
import org.ssemchenko.restservice.repository.mapper.impl.StudentResultSetMapperImpl;
import org.ssemchenko.restservice.util.SqlString;

import java.sql.*;
import java.util.*;

public class FacultyRepositoryImpl implements FacultyRepository {
    public static final FacultyRepositoryImpl INSTANCE = new FacultyRepositoryImpl();
    private ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
    private FacultyResultSetMapper resultMapper = new FacultyResultSetMapperImpl();
    private StudentResultSetMapper studentMapper = new StudentResultSetMapperImpl();
    private String tableName = "university.faculty";

    private FacultyRepositoryImpl() {
    }

    @Override
    public Faculty findById(Integer id) {
        Faculty result;
        String sql = SqlString.FIND_BY_ID.formatted(tableName);
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultMapper.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = SqlString.DELETE_BY_ID.formatted(tableName);
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Faculty> findAll() {
        List<Faculty> result = new ArrayList<>();
        String sqlFaculty = """
            SELECT * 
            FROM university.faculty;
            """;
        String sqlStudent = """
            SELECT *
            FROM university.student
            WHERE faculty = ?;
            """;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlFaculty)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(resultMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlStudent)) {
            for (Faculty faculty : result){
                preparedStatement.setInt(1, faculty.getId());
                var resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    faculty.getStudents().add(studentMapper.map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Faculty save(Faculty faculty) {
        String sql = """
            INSERT INTO university.faculty (name) VALUES (?);
            """;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, faculty.getName());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                faculty.setId(generatedKeys.getInt(1));
            } else throw new RuntimeException();
            return faculty;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static FacultyRepositoryImpl getInstance(){
        return INSTANCE;
    }

    public void setConnectionManager(ConnectionManagerImpl connectionManager) {
        this.connectionManager = connectionManager;
    }
}
