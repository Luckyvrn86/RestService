package ServiceLayerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssemchenko.restservice.entity.Faculty;
import org.ssemchenko.restservice.repository.FacultyRepository;
import org.ssemchenko.restservice.service.impl.FacultyServiceImpl;
import org.ssemchenko.restservice.service.dto.FacultyDto;
import org.ssemchenko.restservice.service.mapper.FacultyDtomapperImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    @Mock
    FacultyRepository repository;
    @Mock
    FacultyDtomapperImpl mapper;

    @InjectMocks
    FacultyServiceImpl facultyService;
    @Test
    void findAll() {
        Faculty faculty = new Faculty();
        faculty.setName("name");
        faculty.setId(1);
        FacultyDto facultyDto = new FacultyDto(1, "name");
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(faculty));
        Mockito.when(mapper.map(faculty)).thenReturn(facultyDto);
        List<FacultyDto> facultyList = facultyService.findAll();
        assertEquals(faculty.getName(), facultyList.get(0).getName());
    }

    @Test
    void findById() {
        Faculty faculty = new Faculty();
        faculty.setName("name");
        FacultyDto facultyDto = new FacultyDto(1, "name");
        Mockito.when(repository.getReferenceById(1)).thenReturn(faculty);
        Mockito.when(mapper.map(faculty)).thenReturn(facultyDto);
        assertEquals(facultyService.findById(1).getName(), faculty.getName());
    }

    @Test
    void deleteById() {
        facultyService.deleteById(1);
        Mockito.verify(repository, Mockito.times(1)).deleteById(1);
    }
    @Test
    void save(){
        Faculty faculty = new Faculty();
        faculty.setName("Test");
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setName("Test");
        Mockito.when(repository.save(faculty)).thenReturn(faculty);
        Mockito.when(mapper.map(faculty)).thenReturn(facultyDto);
        Mockito.when(mapper.map(facultyDto)).thenReturn(faculty);
        assertEquals(faculty.getName(), facultyService.save(facultyDto).getName());
    }
}
