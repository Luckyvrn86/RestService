package ControllerLayerTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssemchenko.restservice.controller.StudentController;
import org.ssemchenko.restservice.service.impl.StudentServiceImpl;
import org.ssemchenko.restservice.service.dto.StudentDto;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    StudentServiceImpl service;
    @InjectMocks
    StudentController controller;


    @Test
    void findAll()  {
        List<StudentDto> studentDtoList = new ArrayList<>();
        Mockito.when(service.findAll()).thenReturn(studentDtoList);
        List<StudentDto> result = controller.findAll();
        Assertions.assertEquals(studentDtoList, result);
    }
    @Test
    void findById() {
        int id = 1;
        StudentDto facultyDto = new StudentDto(id, "name");
        Mockito.when(service.findById(id)).thenReturn(facultyDto);
        StudentDto result = controller.findById(id);
        Assertions.assertEquals(facultyDto, result);
    }

    @Test
    void save() {
        StudentDto studentDto = new StudentDto(0, "name");
        Mockito.when(service.save(studentDto)).thenReturn(studentDto);
        StudentDto result = controller.save("name");
        Assertions.assertEquals(studentDto, result);
    }

    @Test
    void deleteById() {
        controller.delete(1);
        Mockito.verify(service, Mockito.times(1)).deleteById(1);
    }
}
































