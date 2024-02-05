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
import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.repository.impl.FacultyRepositoryImpl;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@ExtendWith(MockitoExtension.class)
public class FacultyRepositoryTest {
    FacultyRepositoryImpl facultyRepository = FacultyRepositoryImpl.getInstance();
    ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
    @Mock
    Faculty mockFaculty;
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
        facultyRepository.setConnectionManager(connectionManager);
    }
    @Test
    void findById() {
        String name = "Технический";
        Faculty faculty = facultyRepository.findById(1);
        assertEquals(name, faculty.getName());
    }
    @Test
    void deleteById(){
        assertTrue(facultyRepository.deleteById(2));
    }

    @Test
    void findAll(){
        List<Faculty> faculties = facultyRepository.findAll();
        String[] actualFaculties = {"Технический", "Биологический", "Гуманитарный"};
        assertArrayEquals(actualFaculties, faculties.stream().map(Faculty::getName).toArray());
    }

    @Test
    void save(){
        Mockito.doReturn("Физический").when(mockFaculty).getName();
        assertEquals(mockFaculty.getName(), facultyRepository.save(mockFaculty).getName());
    }
}























