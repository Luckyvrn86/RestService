package RepositoryLayerTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssemchenko.restservice.db.ConnectionManagerImpl;
import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.repository.impl.StudentRepositoryImpl;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@ExtendWith(MockitoExtension.class)
public class StudentRepositoryTest {
    StudentRepositoryImpl studentRepository = StudentRepositoryImpl.getInstance();
    ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
    @Mock
    Student mockStudent;
    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("university")
            .withUsername("user")
            .withPassword("password")
            .withInitScript("db.sql");
    @BeforeAll
    static void beforeAll(){
        mysql.start();
    }
    @AfterAll
    static void afterAll(){
        mysql.stop();
    }
    @BeforeEach
    void setUp() {
        connectionManager.setUrl(mysql.getJdbcUrl());
        connectionManager.setUsername(mysql.getUsername());
        connectionManager.setPassword(mysql.getPassword());
        studentRepository.setConnectionManager(connectionManager);
    }

    @Test
    void findById() {
        String name = "Иванов Иван";
        Student student = studentRepository.findById(1);
        assertEquals(name, student.getName());
    }
    @Test
    void deleteById(){
        assertTrue(studentRepository.deleteById(2));
    }

    @Test
    void findAll(){
        List<Student> students = studentRepository.findAll();
        String[] actualStudents = {"Иванов Иван", "Петр Петров", "Сергей Сергеев"
                , "Василий Васильев", "Света Светикова", "Анна Каренина"};
        assertArrayEquals(actualStudents, students.stream().map(Student::getName).toArray());
    }

    @Test
    void save(){
        Mockito.doReturn("Семен Семенов").when(mockStudent).getName();
        assertEquals(mockStudent.getName(), studentRepository.save(mockStudent).getName());
    }

}





























