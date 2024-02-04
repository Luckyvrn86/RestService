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
import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.repository.impl.FacultyRepositoryImpl;
import org.ssemchenko.restservice.repository.impl.StudentRepositoryImpl;
import org.ssemchenko.restservice.repository.impl.TeacherRepositoryImpl;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@ExtendWith(MockitoExtension.class)
class RepositoryTest {
    @Mock
    Faculty mockFaculty;
    @Mock
    Student mockStudent;
    @Mock
    Teacher mockTeacher;
    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("university")
            .withUsername("user")
            .withPassword("password")
            .withInitScript("db.sql");
    FacultyRepositoryImpl facultyRepository = FacultyRepositoryImpl.getInstance();
    StudentRepositoryImpl studentRepository = StudentRepositoryImpl.getInstance();
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
        facultyRepository.setConnectionManager(connectionManager);
        studentRepository.setConnectionManager(connectionManager);
        teacherRepository.setConnectionManager(connectionManager);
    }

    @Test
    void facultyFindById() {
        String name = "Технический";
        Faculty faculty = facultyRepository.findById(1);
        assertEquals(name, faculty.getName());
    }
    @Test
    void facultysDeleteById(){
        assertTrue(facultyRepository.deleteById(2));
    }

    @Test
    void facultyFindAll(){
        List<Faculty> faculties = facultyRepository.findAll();
        String[] actualFaculties = {"Технический", "Биологический", "Гуманитарный", "Физический"};
        assertArrayEquals(actualFaculties, faculties.stream().map(Faculty::getName).toArray());
    }

    @Test
    void facultySave(){
        Mockito.doReturn("Физический").when(mockFaculty).getName();
        assertEquals(mockFaculty.getName(), facultyRepository.save(mockFaculty).getName());
    }

    @Test
    void studentFindById() {
        String name = "Иванов Иван";
        Student student = studentRepository.findById(1);
        assertEquals(name, student.getName());
    }
    @Test
    void studentsDeleteById(){
        assertTrue(studentRepository.deleteById(2));
    }

    @Test
    void studentFindAll(){
        List<Student> students = studentRepository.findAll();
        String[] actualStudents = {"Иванов Иван", "Петр Петров", "Сергей Сергеев"
                , "Василий Васильев", "Света Светикова", "Анна Каренина", "Семен Семенов"};
        assertArrayEquals(actualStudents, students.stream().map(Student::getName).toArray());
    }

    @Test
    void studentSave(){
        Mockito.doReturn("Семен Семенов").when(mockStudent).getName();
        assertEquals(mockStudent.getName(), studentRepository.save(mockStudent).getName());
    }

    @Test
    void teacherFindById() {
        String name = "Иван Иванович";
        Teacher teacher = teacherRepository.findById(1);
        assertEquals(name, teacher.getName());
    }
    @Test
    void teachersDeleteById(){
        assertTrue(teacherRepository.deleteById(2));
    }

    @Test
    void teacherFindAll(){
        List<Teacher> teachers = teacherRepository.findAll();
        String[] actualTeachers = {"Иван Иванович", "Петр Петрович", "Степан Степанович"
                , "Илья Ильич", "Сергей Сергеевич", "Василий Васильевич"};
        assertArrayEquals(actualTeachers, teachers.stream().map(Teacher::getName).toArray());
    }

    @Test
    void teacherSave(){
        Mockito.doReturn("Семен Семенов").when(mockTeacher).getName();
        assertEquals(mockTeacher.getName(), teacherRepository.save(mockTeacher).getName());
    }
}























