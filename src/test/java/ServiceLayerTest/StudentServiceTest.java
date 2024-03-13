package ServiceLayerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssemchenko.restservice.entity.Student;
import org.ssemchenko.restservice.repository.StudentRepository;
import org.ssemchenko.restservice.service.dto.StudentDto;
import org.ssemchenko.restservice.service.impl.StudentServiceImpl;
import org.ssemchenko.restservice.service.mapper.StudentDtomapperImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    StudentRepository repository;
    @Mock
    StudentDtomapperImpl mapper;

    @InjectMocks
    StudentServiceImpl studentService;
    @Test
    void findAll() {
        Student student = new Student();
        student.setName("name");
        student.setId(1);
        StudentDto studentDto = new StudentDto(1, "name");
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(student));
        Mockito.when(mapper.map(student)).thenReturn(studentDto);
        List<StudentDto> studentList = studentService.findAll();
        assertEquals(student.getName(), studentList.get(0).getName());
    }

    @Test
    void findById() {
        Student student = new Student();
        student.setName("name");
        StudentDto studentDto = new StudentDto(1, "name");
        Mockito.when(repository.getReferenceById(1)).thenReturn(student);
        Mockito.when(mapper.map(student)).thenReturn(studentDto);
        assertEquals(studentService.findById(1).getName(), student.getName());
    }

    @Test
    void deleteById() {
        studentService.deleteById(1);
        Mockito.verify(repository, Mockito.times(1)).deleteById(1);
    }
    @Test
    void save(){
        Student student = new Student();
        student.setName("Test");
        StudentDto studentDto = new StudentDto();
        studentDto.setName("Test");
        Mockito.when(repository.save(student)).thenReturn(student);
        Mockito.when(mapper.map(student)).thenReturn(studentDto);
        Mockito.when(mapper.map(studentDto)).thenReturn(student);
        assertEquals(student.getName(), studentService.save(studentDto).getName());
    }
}
