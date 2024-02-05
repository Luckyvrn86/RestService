package ServletLayerTest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.service.impl.FacultyServiceImpl;
import org.ssemchenko.restservice.service.impl.StudentServiceImpl;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;
import org.ssemchenko.restservice.servlet.dto.StudentDto;
import org.ssemchenko.restservice.servlet.student.DeleteById;
import org.ssemchenko.restservice.servlet.student.FindAll;
import org.ssemchenko.restservice.servlet.student.FindById;
import org.ssemchenko.restservice.servlet.student.Save;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class StudentServletTest {

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    StudentServiceImpl studentService;
    @Mock
    FacultyServiceImpl facultyService;
    @InjectMocks
    FindAll findAll;
    @InjectMocks
    FindById findById;
    @InjectMocks
    DeleteById deleteById;
    @InjectMocks
    Save save;

    @Test
    void findAll() throws ServletException, IOException {
        List<StudentDto> list = new ArrayList<>();
        FacultyDto faculty = new FacultyDto();
        faculty.setName("first");
        StudentDto first = new StudentDto();
        StudentDto second = new StudentDto();
        first.setName("first");
        first.setFacultyId(1);
        second.setFacultyId(1);
        second.setName("second");
        list.add(first);
        list.add(second);
        Mockito.when(studentService.findAll()).thenReturn(list);
        Mockito.when(facultyService.findById(1)).thenReturn(faculty);
        findAll.setStudentService(studentService);
        findAll.setFacultyService(facultyService);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(writer);
        findAll.doGet(req, resp);
        String s = stringWriter.toString();
        Assertions.assertTrue(s.contains("first"));
    }
    @Test
    void findById() throws IOException, ServletException {
        StudentDto first = new StudentDto();
        first.setName("first");
        first.setFacultyId(1);
        FacultyDto faculty = new FacultyDto();
        faculty.setName("first");
        Mockito.when(studentService.findById(1)).thenReturn(first);
        Mockito.when(facultyService.findById(1)).thenReturn(faculty);
        findById.setStudentService(studentService);
        findById.setFacultyService(facultyService);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(writer);
        Mockito.when(req.getParameter("id")).thenReturn("1");
        findById.doGet(req, resp);
        String s = stringWriter.toString();
        Assertions.assertTrue(s.contains("first"));
    }
    @Test
    void deleteById() throws IOException, ServletException {
        Mockito.when(studentService.deleteById(1)).thenReturn(true);
        deleteById.setStudentService(studentService);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(writer);
        Mockito.when(req.getParameter("id")).thenReturn("1");
        deleteById.doGet(req, resp);
        String s = stringWriter.toString();
        Assertions.assertTrue(s.contains("удален"));
    }
    @Test
    void save() throws IOException, ServletException {
        Student student = new Student();
        student.setName("test");
        student.setId(70);
        StudentDto studentDto = new StudentDto();
        studentDto.setName("test");
        studentDto.setId(70);
        Mockito.when(studentService.save(any())).thenReturn(studentDto);
        save.setStudentService(studentService);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(writer);
        Mockito.when(req.getParameter("name")).thenReturn(student.getName());
        Mockito.when(req.getParameter("fid")).thenReturn("70");
        save.doGet(req, resp);
        String s = stringWriter.toString();
        Assertions.assertTrue(s.contains("test"));
    }
}
































