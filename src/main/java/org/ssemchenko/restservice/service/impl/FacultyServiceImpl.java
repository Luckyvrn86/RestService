package org.ssemchenko.restservice.service.impl;

import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.repository.impl.FacultyRepositoryImpl;
import org.ssemchenko.restservice.service.FacultyService;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;
import org.ssemchenko.restservice.servlet.mapper.FacultyDtomapperImpl;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepositoryImpl facultyRepository = FacultyRepositoryImpl.getInstance();
    private final FacultyDtomapperImpl facultyDtomapper = new FacultyDtomapperImpl();
    private static final FacultyServiceImpl INSTANCE = new FacultyServiceImpl();

    private FacultyServiceImpl() {
    }

    @Override
    public Faculty findById(int id) {
        return facultyRepository.findById(id);
    }

    @Override
    public boolean deleteById(int id) {
        return facultyRepository.deleteById(id);
    }

    @Override
    public List<FacultyDto> findAll() {
        return facultyRepository.findAll().stream()
                .map(facultyDtomapper::map)
                .collect(toList());
    }

    @Override
    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public static FacultyServiceImpl getInstance(){
        return INSTANCE;
    }
}
