package org.ssemchenko.restservice.service;

import org.ssemchenko.restservice.service.dto.FacultyDto;

import java.util.List;

public interface FacultyService {

    FacultyDto findById(int id);
    void deleteById(int id);
    List<FacultyDto> findAll();
    FacultyDto save(FacultyDto faculty);
}
