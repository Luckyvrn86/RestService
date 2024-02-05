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
import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.service.impl.TeacherServiceImpl;
import org.ssemchenko.restservice.servlet.dto.TeacherDto;
import org.ssemchenko.restservice.servlet.teacher.DeleteById;
import org.ssemchenko.restservice.servlet.teacher.FindAll;
import org.ssemchenko.restservice.servlet.teacher.FindById;
import org.ssemchenko.restservice.servlet.teacher.Save;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TeacherServletTest {

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    TeacherServiceImpl teacherService;
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
        List<TeacherDto> list = new ArrayList<>();
        TeacherDto first = new TeacherDto();
        TeacherDto second = new TeacherDto();
        first.setName("first");
        second.setName("second");
        list.add(first);
        list.add(second);
        Mockito.when(teacherService.findAll()).thenReturn(list);
        findAll.setTeacherService(teacherService);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(writer);
        findAll.doGet(req, resp);
        String s = stringWriter.toString();
        Assertions.assertTrue(s.contains("first"));
    }
    @Test
    void findById() throws IOException, ServletException {
        TeacherDto first = new TeacherDto();
        first.setName("first");
        Mockito.when(teacherService.findById(1)).thenReturn(first);
        findById.setTeacherService(teacherService);
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
        Mockito.when(teacherService.deleteById(1)).thenReturn(true);
        deleteById.setTeacherService(teacherService);
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
        Teacher teacher = new Teacher();
        teacher.setName("test");
        teacher.setId(70);
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setName("test");
        teacherDto.setId(70);
        Mockito.when(teacherService.save(any())).thenReturn(teacherDto);
        save.setTeacherService(teacherService);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(writer);
        Mockito.when(req.getParameter("name")).thenReturn(teacher.getName());
        save.doGet(req, resp);
        String s = stringWriter.toString();
        Assertions.assertTrue(s.contains("test"));
    }
}

































