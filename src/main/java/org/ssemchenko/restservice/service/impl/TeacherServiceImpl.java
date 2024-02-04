package org.ssemchenko.restservice.service.impl;

import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.repository.TeacherRepository;
import org.ssemchenko.restservice.repository.impl.TeacherRepositoryImpl;
import org.ssemchenko.restservice.service.TeacherService;
import org.ssemchenko.restservice.servlet.dto.TeacherDto;
import org.ssemchenko.restservice.servlet.mapper.TeacherDtomapper;
import org.ssemchenko.restservice.servlet.mapper.TeacherDtomapperImpl;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherServiceImpl implements TeacherService {
    private static final TeacherServiceImpl INSTANCE = new TeacherServiceImpl();
    private TeacherRepository teacherRepository = TeacherRepositoryImpl.getInstance();
    private final TeacherDtomapper teacherDtomapper = new TeacherDtomapperImpl();
    @Override
    public TeacherDto findById(int id) {
        return teacherDtomapper.map(teacherRepository.findById(id));
    }

    @Override
    public boolean deleteById(int id) {
        return teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherDto> findAll() {
        return teacherRepository.findAll().stream()
                .map(teacherDtomapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDto save(Teacher teacher) {
        return teacherDtomapper.map(teacherRepository.save(teacher));
    }
    public static TeacherServiceImpl getInstance() {return INSTANCE;}

    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
}
