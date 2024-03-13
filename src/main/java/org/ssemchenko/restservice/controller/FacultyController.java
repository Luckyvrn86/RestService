package org.ssemchenko.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssemchenko.restservice.service.FacultyService;
import org.ssemchenko.restservice.service.dto.FacultyDto;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService service;

    @Autowired

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping()
    public List<FacultyDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/find")
    public FacultyDto findById (@RequestBody Integer id){
        return service.findById(id);
    }

    @GetMapping("/add")
    public FacultyDto save(@RequestBody String name){
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setName(name);
        return service.save(facultyDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Integer id){
        service.deleteById(id);
    }
}
