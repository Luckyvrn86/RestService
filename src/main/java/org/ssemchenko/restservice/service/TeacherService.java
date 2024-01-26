package org.ssemchenko.restservice.service;

import org.ssemchenko.restservice.model.Teacher;

public interface TeacherService {
    Teacher save(Teacher teacher);
    Teacher findById(int id);

}
