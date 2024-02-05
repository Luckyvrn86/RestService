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
import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.repository.impl.TeacherRepositoryImpl;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@ExtendWith(MockitoExtension.class)
class TeacherRepositoryTest {

    @Mock
    Teacher mockTeacher;
    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("university")
            .withUsername("user")
            .withPassword("password")
            .withInitScript("db.sql");

    TeacherRepositoryImpl teacherRepository = TeacherRepositoryImpl.getInstance();
    ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
    @BeforeAll
    static void beforeAll(){
        mysql.start();
    }
    @AfterAll
    static void afterAll(){
        mysql.stop();
    }
    @BeforeEach
    void setUp(){
        connectionManager.setUrl(mysql.getJdbcUrl());
        connectionManager.setUsername(mysql.getUsername());
        connectionManager.setPassword(mysql.getPassword());
        teacherRepository.setConnectionManager(connectionManager);
    }


    @Test
    void findById() {
        String name = "Иван Иванович";
        Teacher teacher = teacherRepository.findById(1);
        assertEquals(name, teacher.getName());
    }
    @Test
    void deleteById(){
        assertTrue(teacherRepository.deleteById(2));
    }

    @Test
    void findAll(){
        List<Teacher> teachers = teacherRepository.findAll();
        String[] actualTeachers = {"Иван Иванович", "Петр Петрович", "Степан Степанович"
                , "Илья Ильич", "Сергей Сергеевич", "Василий Васильевич"};
        assertArrayEquals(actualTeachers, teachers.stream().map(Teacher::getName).toArray());
    }

    @Test
    void save(){
        Mockito.doReturn("Семен Семенов").when(mockTeacher).getName();
        assertEquals(mockTeacher.getName(), teacherRepository.save(mockTeacher).getName());
    }
}























