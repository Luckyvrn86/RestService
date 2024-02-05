package ServiceLayerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.repository.impl.TeacherRepositoryImpl;
import org.ssemchenko.restservice.service.impl.TeacherServiceImpl;
import org.ssemchenko.restservice.servlet.dto.TeacherDto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
      @Mock
    TeacherRepositoryImpl teacherRepository;
    @InjectMocks
    TeacherServiceImpl teacherService;

    @Test
    void findAll() {
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
    void findById() {
        Teacher teacher = new Teacher();
        teacher.setName("Test");
        teacherService.setTeacherRepository(teacherRepository);
        Mockito.when(teacherRepository.findById(1)).thenReturn(teacher);
        assertEquals(teacherService.findById(1).getName(), teacher.getName());
    }

    @Test
    void deleteById() {
        Mockito.when(teacherRepository.deleteById(1)).thenReturn(true);
        teacherService.setTeacherRepository(teacherRepository);
        teacherService.deleteById(1);
        Mockito.verify(teacherRepository, Mockito.times(1)).deleteById(1);
    }
    @Test
    void save(){
        Teacher teacher = new Teacher();
        teacher.setName("Test");
        teacherService.setTeacherRepository(teacherRepository);
        Mockito.when(teacherRepository.save(teacher)).thenReturn(teacher);
        assertEquals(teacher.getName(), teacherService.save(teacher).getName());
    }
}


















