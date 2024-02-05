package ServiceLayerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.repository.impl.FacultyRepositoryImpl;
import org.ssemchenko.restservice.service.impl.FacultyServiceImpl;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    @Mock
    FacultyRepositoryImpl facultyRepository;
    @InjectMocks
    FacultyServiceImpl facultyService;
    @Test
    void findAll() {
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
    void findById() {
        Faculty faculty = new Faculty();
        faculty.setName("Test");
        facultyService.setFacultyRepository(facultyRepository);
        Mockito.when(facultyRepository.findById(1)).thenReturn(faculty);
        assertEquals(facultyService.findById(1).getName(), faculty.getName());
    }

    @Test
    void deleteById() {
        Mockito.when(facultyRepository.deleteById(1)).thenReturn(true);
        facultyService.setFacultyRepository(facultyRepository);
        facultyService.deleteById(1);
        Mockito.verify(facultyRepository, Mockito.times(1)).deleteById(1);
    }
    @Test
    void save(){
        Faculty faculty = new Faculty();
        faculty.setName("Test");
        facultyService.setFacultyRepository(facultyRepository);
        Mockito.when(facultyRepository.save(faculty)).thenReturn(faculty);
        assertEquals(faculty.getName(), facultyService.save(faculty).getName());
    }
}
