package ControllerLayerTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssemchenko.restservice.controller.TeacherController;
import org.ssemchenko.restservice.service.impl.TeacherServiceImpl;
import org.ssemchenko.restservice.service.dto.TeacherDto;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {

    @Mock
    TeacherServiceImpl service;
    @InjectMocks
    TeacherController controller;


    @Test
    void findAll()  {
        List<TeacherDto> teacherDtoList = new ArrayList<>();
        Mockito.when(service.findAll()).thenReturn(teacherDtoList);
        List<TeacherDto> result = controller.findAll();
        Assertions.assertEquals(teacherDtoList, result);
    }
    @Test
    void findById() {
        int id = 1;
        TeacherDto teacherDto = new TeacherDto(id, "name");
        Mockito.when(service.findById(id)).thenReturn(teacherDto);
        TeacherDto result = controller.findById(id);
        Assertions.assertEquals(teacherDto, result);
    }

    @Test
    void save() {
        TeacherDto teacherDto = new TeacherDto(0, "name");
        Mockito.when(service.save(teacherDto)).thenReturn(teacherDto);
        TeacherDto result = controller.save("name");
        Assertions.assertEquals(teacherDto, result);
    }

    @Test
    void deleteById() {
        controller.delete(1);
        Mockito.verify(service, Mockito.times(1)).deleteById(1);
    }
}

































