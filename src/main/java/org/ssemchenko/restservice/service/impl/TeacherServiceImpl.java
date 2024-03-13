package org.ssemchenko.restservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssemchenko.restservice.repository.TeacherRepository;
import org.ssemchenko.restservice.service.TeacherService;
import org.ssemchenko.restservice.service.dto.TeacherDto;
import org.ssemchenko.restservice.service.mapper.TeacherDtomapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;
    private final TeacherDtomapper mapper;

    @Autowired
    public TeacherServiceImpl(TeacherRepository repository, TeacherDtomapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }



    @Override
    public TeacherDto findById(int id) {
        return mapper.map(repository.getReferenceById(id));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<TeacherDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDto save(TeacherDto teacher) {
        return mapper.map(repository.save(mapper.map(teacher)));
    }
}
