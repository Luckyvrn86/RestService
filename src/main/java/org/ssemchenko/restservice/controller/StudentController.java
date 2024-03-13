package org.ssemchenko.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssemchenko.restservice.service.StudentService;
import org.ssemchenko.restservice.service.dto.StudentDto;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    @Autowired

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping()
    public List<StudentDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/find")
    public StudentDto findById (@RequestBody Integer id){
        return service.findById(id);
    }

    @GetMapping("/add")
    public StudentDto save(@RequestBody String name){
        StudentDto studentDto = new StudentDto();
        studentDto.setName(name);
        return service.save(studentDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Integer id){
        service.deleteById(id);
    }
}
