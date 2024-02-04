import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.repository.FacultyRepository;
import org.ssemchenko.restservice.repository.impl.FacultyRepositoryImpl;
import org.ssemchenko.restservice.repository.impl.StudentRepositoryImpl;
import org.ssemchenko.restservice.repository.impl.TeacherRepositoryImpl;
import org.ssemchenko.restservice.service.FacultyService;
import org.ssemchenko.restservice.service.impl.FacultyServiceImpl;
import org.ssemchenko.restservice.service.impl.StudentServiceImpl;
import org.ssemchenko.restservice.service.impl.TeacherServiceImpl;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;
import org.ssemchenko.restservice.servlet.dto.StudentDto;
import org.ssemchenko.restservice.servlet.dto.TeacherDto;
import org.ssemchenko.restservice.servlet.mapper.FacultyDtomapperImpl;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    FacultyRepositoryImpl facultyRepository;
    @Mock
    StudentRepositoryImpl studentRepository;
    @Mock
    TeacherRepositoryImpl teacherRepository;
    @InjectMocks
    FacultyServiceImpl facultyService;
    @InjectMocks
    StudentServiceImpl studentService;
    @InjectMocks
    TeacherServiceImpl teacherService;
    @Test
    void facultyFindAll() {
        List<Faculty> list = new ArrayList<>();
        Faculty first = new Faculty();
        Faculty second = new Faculty();
        Faculty third = new Faculty();
        first.setName("first");
        second.setName("second");
        third.setName("third");
        list.add(first);
        list.add(second);
        list.add(third);
        facultyService.setFacultyRepository(facultyRepository);
        Mockito.when(facultyRepository.findAll()).thenReturn(list);
        List<FacultyDto> facultyList = facultyService.findAll();
        assertEquals(first.getName(), facultyList.get(0).getName());
    }

    @Test
    void facultyFindById() {
        Faculty faculty = new Faculty();
        faculty.setName("Test");
        facultyService.setFacultyRepository(facultyRepository);
        Mockito.when(facultyRepository.findById(1)).thenReturn(faculty);
        assertEquals(facultyService.findById(1).getName(), faculty.getName());
    }

    @Test
    void facultyDeleteById() {
        Mockito.when(facultyRepository.deleteById(1)).thenReturn(true);
        facultyService.setFacultyRepository(facultyRepository);
        facultyService.deleteById(1);
        Mockito.verify(facultyRepository, Mockito.times(1)).deleteById(1);
    }
    @Test
    void facultySave(){
        Faculty faculty = new Faculty();
        faculty.setName("Test");
        facultyService.setFacultyRepository(facultyRepository);
        Mockito.when(facultyRepository.save(faculty)).thenReturn(faculty);
        assertEquals(faculty.getName(), facultyService.save(faculty).getName());
    }

    @Test
    void studentFindAll() {
        List<Student> list = new ArrayList<>();
        Student first = new Student();
        Student second = new Student();
        Student third = new Student();
        first.setName("first");
        second.setName("second");
        third.setName("third");
        list.add(first);
        list.add(second);
        list.add(third);
        studentService.setStudentRepository(studentRepository);
        Mockito.when(studentRepository.findAll()).thenReturn(list);
        List<StudentDto> studentList = studentService.findAll();
        assertEquals(first.getName(), studentList.get(0).getName());
    }

    @Test
    void studentFindById() {
        Student student = new Student();
        student.setName("Test");
        studentService.setStudentRepository(studentRepository);
        Mockito.when(studentRepository.findById(1)).thenReturn(student);
        assertEquals(studentService.findById(1).getName(), student.getName());
    }

    @Test
    void studentDeleteById() {
        Mockito.when(studentRepository.deleteById(1)).thenReturn(true);
        studentService.setStudentRepository(studentRepository);
        studentService.deleteById(1);
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(1);
    }
    @Test
    void studentSave(){
        Student student = new Student();
        student.setName("Test");
        studentService.setStudentRepository(studentRepository);
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        assertEquals(student.getName(), studentService.save(student).getName());
    }
    @Test
    void teacherFindAll() {
        List<Teacher> list = new ArrayList<>();
        Teacher first = new Teacher();
        Teacher second = new Teacher();
        Teacher third = new Teacher();
        first.setName("first");
        second.setName("second");
        third.setName("third");
        list.add(first);
        list.add(second);
        list.add(third);
        teacherService.setTeacherRepository(teacherRepository);
        Mockito.when(teacherRepository.findAll()).thenReturn(list);
        List<TeacherDto> teacherList = teacherService.findAll();
        assertEquals(first.getName(), teacherList.get(0).getName());
    }

    @Test
    void teacherFindById() {
        Teacher teacher = new Teacher();
        teacher.setName("Test");
        teacherService.setTeacherRepository(teacherRepository);
        Mockito.when(teacherRepository.findById(1)).thenReturn(teacher);
        assertEquals(teacherService.findById(1).getName(), teacher.getName());
    }

    @Test
    void teacherDeleteById() {
        Mockito.when(teacherRepository.deleteById(1)).thenReturn(true);
        teacherService.setTeacherRepository(teacherRepository);
        teacherService.deleteById(1);
        Mockito.verify(teacherRepository, Mockito.times(1)).deleteById(1);
    }
    @Test
    void teacherSave(){
        Teacher teacher = new Teacher();
        teacher.setName("Test");
        teacherService.setTeacherRepository(teacherRepository);
        Mockito.when(teacherRepository.save(teacher)).thenReturn(teacher);
        assertEquals(teacher.getName(), teacherService.save(teacher).getName());
    }
}


















