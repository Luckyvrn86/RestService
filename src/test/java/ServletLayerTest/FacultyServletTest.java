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
import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.service.impl.FacultyServiceImpl;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;
import org.ssemchenko.restservice.servlet.faculty.DeleteById;
import org.ssemchenko.restservice.servlet.faculty.FindAll;
import org.ssemchenko.restservice.servlet.faculty.FindById;
import org.ssemchenko.restservice.servlet.faculty.Save;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class FacultyServletTest {
    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
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
        List<FacultyDto> list = new ArrayList<>();
        FacultyDto first = new FacultyDto();
        FacultyDto second = new FacultyDto();
        first.setName("first");
        second.setName("second");
        list.add(first);
        list.add(second);
        Mockito.when(facultyService.findAll()).thenReturn(list);
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
        FacultyDto first = new FacultyDto();
        first.setName("first");
        Mockito.when(facultyService.findById(1)).thenReturn(first);
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
        Mockito.when(facultyService.deleteById(1)).thenReturn(true);
        deleteById.setFacultyService(facultyService);
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
        Faculty faculty = new Faculty();
        faculty.setName("test");
        faculty.setId(70);
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setName("test");
        facultyDto.setId(70);
        Mockito.when(facultyService.save(any())).thenReturn(facultyDto);
        save.setFacultyService(facultyService);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(writer);
        Mockito.when(req.getParameter("name")).thenReturn(faculty.getName());
        save.doGet(req, resp);
        String s = stringWriter.toString();
        Assertions.assertTrue(s.contains("test"));
    }
}



























