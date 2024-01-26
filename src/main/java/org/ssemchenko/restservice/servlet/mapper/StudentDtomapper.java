package org.ssemchenko.restservice.servlet.mapper;

import org.ssemchenko.restservice.model.Student;
import org.ssemchenko.restservice.servlet.dto.IncomingDto;
import org.ssemchenko.restservice.servlet.dto.OutGoingDto;

public interface StudentDtomapper {
    Student map(IncomingDto incomingDto);
    OutGoingDto map(Student student);
}
