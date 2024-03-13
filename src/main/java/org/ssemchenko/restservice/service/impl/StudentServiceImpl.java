package org.ssemchenko.restservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssemchenko.restservice.repository.StudentRepository;
import org.ssemchenko.restservice.service.StudentService;
import org.ssemchenko.restservice.service.dto.StudentDto;
import org.ssemchenko.restservice.service.mapper.StudentDtomapper;

import javax.transaction.Transactional;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private final StudentDtomapper mapper;

    @Autowired
    public StudentServiceImpl(StudentRepository repository, StudentDtomapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StudentDto findById(int id) {
        return mapper.map(repository.getReferenceById(id));
    }

    @Override
    public void deleteById(int id){ repository.deleteById(id);
    }

    @Override
    public List<StudentDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::map)
                .collect(toList());

    }

    @Override
    public StudentDto save(StudentDto student) {
        return mapper.map(repository.save(mapper.map(student)));
    }
}
