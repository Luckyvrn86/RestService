package ServiceLayerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.repository.impl.StudentRepositoryImpl;
import org.ssemchenko.restservice.service.impl.StudentServiceImpl;
import org.ssemchenko.restservice.servlet.dto.StudentDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    StudentRepositoryImpl studentRepository;
    @InjectMocks
    StudentServiceImpl studentService;
    @Test
    void findAll() {
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
    void findById() {
        Student student = new Student();
        student.setName("Test");
        studentService.setStudentRepository(studentRepository);
        Mockito.when(studentRepository.findById(1)).thenReturn(student);
        assertEquals(studentService.findById(1).getName(), student.getName());
    }

    @Test
    void deleteById() {
        Mockito.when(studentRepository.deleteById(1)).thenReturn(true);
        studentService.setStudentRepository(studentRepository);
        studentService.deleteById(1);
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(1);
    }
    @Test
    void save(){
        Student student = new Student();
        student.setName("Test");
        studentService.setStudentRepository(studentRepository);
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        assertEquals(student.getName(), studentService.save(student).getName());
    }
}
