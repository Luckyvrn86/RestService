package org.ssemchenko.restservice.service;

import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;

import java.util.List;

public interface FacultyService {

    Faculty findById(int id);
    boolean deleteById(int id);
    List<FacultyDto> findAll();
    Faculty save(Faculty faculty);
}
