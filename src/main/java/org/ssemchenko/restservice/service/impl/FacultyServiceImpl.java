package org.ssemchenko.restservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssemchenko.restservice.repository.FacultyRepository;
import org.ssemchenko.restservice.service.FacultyService;
import org.ssemchenko.restservice.service.dto.FacultyDto;
import org.ssemchenko.restservice.service.mapper.FacultyDtomapper;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository repository;
    private final FacultyDtomapper mapper;

    @Autowired
    public FacultyServiceImpl(FacultyRepository repository, FacultyDtomapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public FacultyDto findById(int id) {
        return mapper.map(repository.getReferenceById(id));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<FacultyDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::map)
                .collect(toList());
    }

    @Override
    public FacultyDto save(FacultyDto faculty) {
        return mapper.map(repository.save(mapper.map(faculty)));
    }

}
