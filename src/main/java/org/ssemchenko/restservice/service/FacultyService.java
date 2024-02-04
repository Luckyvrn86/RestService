package org.ssemchenko.restservice.service;

import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;

import java.util.List;

public interface FacultyService {

    FacultyDto findById(int id);
    boolean deleteById(int id);
    List<FacultyDto> findAll();
    FacultyDto save(Faculty faculty);
}
