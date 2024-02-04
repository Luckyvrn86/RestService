package org.ssemchenko.restservice.repository.impl;

import org.ssemchenko.restservice.db.ConnectionManager;
import org.ssemchenko.restservice.db.ConnectionManagerImpl;
import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.repository.TeacherRepository;
import org.ssemchenko.restservice.repository.mapper.TeacherResultSetMapper;
import org.ssemchenko.restservice.repository.mapper.impl.StudentMapperFromTeacher;
import org.ssemchenko.restservice.repository.mapper.impl.TeacherResultSetMapperImpl;
import org.ssemchenko.restservice.util.SqlString;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryImpl implements TeacherRepository {
    public static final TeacherRepositoryImpl INSTANCE = new TeacherRepositoryImpl();
    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private TeacherResultSetMapper resultMapper = new TeacherResultSetMapperImpl();
    private StudentMapperFromTeacher studentMapperFromTeacher = new StudentMapperFromTeacher();
    private String tableName = "university.teacher";

    private TeacherRepositoryImpl() {
    }

    @Override
    public Teacher findById(Integer id) {
        Teacher result;
        String sql = SqlString.FIND_BY_ID.formatted(tableName);
        String sqlStudents = """
            SELECT *
            FROM university.student_teacher
            WHERE teacher_id = ?
            """;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultMapper.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlStudents)) {
            preparedStatement.setInt(1, result.getId());
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.getStudents().add(studentMapperFromTeacher.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
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
    public List<Teacher> findAll() {
        List<Teacher> result = new ArrayList<>();
        String sqlStudents = """
            SELECT * 
            FROM university.teacher;
            """;
        String sqlTeachers = """
            SELECT *
            FROM university.student_teacher
            WHERE teacher_id = ?
            """;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlStudents)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(resultMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlTeachers)) {
            for (Teacher teacher : result) {
                preparedStatement.setInt(1, teacher.getId());
                var resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    teacher.getStudents().add(studentMapperFromTeacher.map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public Teacher save(Teacher teacher) {

        String sql = """
            INSERT INTO university.teacher (name) VALUES (?);
            """;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                teacher.setId(generatedKeys.getInt(1));
            } else throw new RuntimeException();
            return teacher;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static TeacherRepositoryImpl getInstance() {return INSTANCE;}

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}






























