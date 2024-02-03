package org.ssemchenko.restservice.service.impl;

import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.repository.impl.StudentRepositoryImpl;
import org.ssemchenko.restservice.service.StudentService;
import org.ssemchenko.restservice.servlet.dto.StudentDto;
import org.ssemchenko.restservice.servlet.mapper.StudentDtomapper;
import org.ssemchenko.restservice.servlet.mapper.StudentDtomapperImpl;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class StudentServiceImpl implements StudentService {
    private static final StudentServiceImpl INSTANCE = new StudentServiceImpl();
    private final StudentRepositoryImpl studentRepository = StudentRepositoryImpl.getInstance();
    private final StudentDtomapper studentDtomapper = new StudentDtomapperImpl();

    private StudentServiceImpl() {
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public boolean deleteById(int id) {
        return studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream()
                .map(studentDtomapper::map)
                .collect(toList());

    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }
    @Override
    public List<StudentDto> findByFacultyId(int facultyId) {
        return studentRepository.findByFacultyId(facultyId).stream()
                .map(studentDtomapper::map)
                .collect(toList());
    }
    public static StudentServiceImpl getInstance(){return INSTANCE;}
}
