package org.ssemchenko.restservice.servlet.mapper;

import org.ssemchenko.restservice.model.Teacher;
import org.ssemchenko.restservice.servlet.dto.IncomingDto;
import org.ssemchenko.restservice.servlet.dto.OutGoingDto;

public interface TeacherDtomapper {
    Teacher map(IncomingDto incomingDto);
    OutGoingDto map(Teacher teacher);
}
