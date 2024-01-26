package org.ssemchenko.restservice.service;

import org.ssemchenko.restservice.model.Faculty;

public interface FacultyService {

    Faculty save(Faculty faculty);
    Faculty findById(int id);
}
