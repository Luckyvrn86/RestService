package ControllerLayerTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssemchenko.restservice.controller.FacultyController;
import org.ssemchenko.restservice.service.impl.FacultyServiceImpl;
import org.ssemchenko.restservice.service.dto.FacultyDto;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class FacultyControllerTest {
    @Mock
    FacultyServiceImpl service;
    @InjectMocks
    FacultyController controller;


    @Test
    void findAll()  {
        List<FacultyDto> facultyDtoList = new ArrayList<>();
        Mockito.when(service.findAll()).thenReturn(facultyDtoList);
        List<FacultyDto> result = controller.findAll();
        Assertions.assertEquals(facultyDtoList, result);
    }
    @Test
    void findById() {
        int id = 1;
        FacultyDto facultyDto = new FacultyDto(id, "Test");
        Mockito.when(service.findById(id)).thenReturn(facultyDto);
        FacultyDto result = controller.findById(id);
        Assertions.assertEquals(facultyDto, result);
    }

    @Test
    void save() {
        FacultyDto facultyDto = new FacultyDto(0, "Test");
        Mockito.when(service.save(facultyDto)).thenReturn(facultyDto);
        FacultyDto result = controller.save("Test");
        Assertions.assertEquals(facultyDto, result);
    }
    @Test
    void deleteById() {
        controller.delete(1);
        Mockito.verify(service, Mockito.times(1)).deleteById(1);
    }
}



























