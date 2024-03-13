package ServiceLayerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssemchenko.restservice.entity.Teacher;
import org.ssemchenko.restservice.repository.TeacherRepository;
import org.ssemchenko.restservice.service.impl.TeacherServiceImpl;
import org.ssemchenko.restservice.service.dto.TeacherDto;
import org.ssemchenko.restservice.service.mapper.TeacherDtomapperImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
    @Mock
    TeacherRepository repository;
    @Mock
    TeacherDtomapperImpl mapper;

    @InjectMocks
    TeacherServiceImpl facultyService;
    @Test
    void findAll() {
        Teacher teacher = new Teacher();
        teacher.setName("name");
        teacher.setId(1);
        TeacherDto teacherDto = new TeacherDto(1, "name");
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(teacher));
        Mockito.when(mapper.map(teacher)).thenReturn(teacherDto);
        List<TeacherDto> teachertList = facultyService.findAll();
        assertEquals(teacher.getName(), teachertList.get(0).getName());
    }

    @Test
    void findById() {
        Teacher teacher = new Teacher();
        teacher.setName("name");
        TeacherDto teacherDto = new TeacherDto(1, "name");
        Mockito.when(repository.getReferenceById(1)).thenReturn(teacher);
        Mockito.when(mapper.map(teacher)).thenReturn(teacherDto);
        assertEquals(facultyService.findById(1).getName(), teacher.getName());
    }

    @Test
    void deleteById() {
        facultyService.deleteById(1);
        Mockito.verify(repository, Mockito.times(1)).deleteById(1);
    }
    @Test
    void save(){
        Teacher teacher = new Teacher();
        teacher.setName("Test");
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setName("Test");
        Mockito.when(repository.save(teacher)).thenReturn(teacher);
        Mockito.when(mapper.map(teacher)).thenReturn(teacherDto);
        Mockito.when(mapper.map(teacherDto)).thenReturn(teacher);
        assertEquals(teacher.getName(), facultyService.save(teacherDto).getName());
    }
}


















