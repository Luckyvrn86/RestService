package org.ssemchenko.restservice.repository.impl;

import org.ssemchenko.restservice.db.ConnectionManager;
import org.ssemchenko.restservice.db.ConnectionManagerImpl;
import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.repository.StudentRepository;
import org.ssemchenko.restservice.repository.mapper.StudentResultSetMapper;
import org.ssemchenko.restservice.repository.mapper.TeacherResultSetMapper;
import org.ssemchenko.restservice.repository.mapper.impl.StudentResultSetMapperImpl;
import org.ssemchenko.restservice.repository.mapper.impl.TeacherMapperFromStudent;
import org.ssemchenko.restservice.repository.mapper.impl.TeacherResultSetMapperImpl;
import org.ssemchenko.restservice.util.SqlString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    public static final StudentRepositoryImpl INSTANCE = new StudentRepositoryImpl();
    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private StudentResultSetMapper resultMapper = new StudentResultSetMapperImpl();
    private TeacherResultSetMapper teacherMapper = new TeacherResultSetMapperImpl();
    private TeacherMapperFromStudent teacherMapperFromStudent = new TeacherMapperFromStudent();
    private String tableName = "university.student";

    private StudentRepositoryImpl() {
    }

    @Override
    public Student findById(Integer id) {
        Student result;
        String sql = SqlString.FIND_BY_ID.formatted(tableName);
        String sqlTeachers = """
            SELECT *
            FROM university.student_teacher
            WHERE student_id = ?
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
             var preparedStatement = connection.prepareStatement(sqlTeachers)) {
                preparedStatement.setInt(1, result.getId());
                var resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    result.getTeachers().add(teacherMapperFromStudent.map(resultSet));
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
    public List<Student> findAll() {
        List<Student> result = new ArrayList<>();
        String sqlStudents = """
            SELECT * 
            FROM university.student;
            """;
        String sqlTeachers = """
            SELECT *
            FROM university.student_teacher
            WHERE student_id = ?
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
            for (Student student : result) {
                preparedStatement.setInt(1, student.getId());
                var resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    student.getTeachers().add(teacherMapperFromStudent.map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public Student save(Student student) {

        String sql = """
            INSERT INTO university.student (name, faculty) VALUES (?, ?);
            """;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getFacultyId());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                student.setId(generatedKeys.getInt(1));
            } else throw new RuntimeException();
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Student> findByFacultyId(int facultyId) {
        List<Student> result = new ArrayList<>();
        String sqlStudents = """
            SELECT * 
            FROM university.student
            WHERE faculty = ?;
            """;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlStudents)) {
            preparedStatement.setInt(1, facultyId);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(resultMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static StudentRepositoryImpl getInstance() {return INSTANCE;}

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}


























