package org.ssemchenko.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssemchenko.restservice.service.TeacherService;
import org.ssemchenko.restservice.service.dto.TeacherDto;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService service;

    @Autowired

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping()
    public List<TeacherDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/find")
    public TeacherDto findById(@RequestBody Integer id) {
        return service.findById(id);
    }

    @GetMapping("/add")
    public TeacherDto save(@RequestBody String name) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setName(name);
        return service.save(teacherDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Integer id) {
        service.deleteById(id);
    }
}
