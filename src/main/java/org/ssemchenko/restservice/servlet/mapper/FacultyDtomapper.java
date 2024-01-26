package org.ssemchenko.restservice.servlet.mapper;

import org.ssemchenko.restservice.model.Faculty;
import org.ssemchenko.restservice.servlet.dto.IncomingDto;
import org.ssemchenko.restservice.servlet.dto.OutGoingDto;

public interface FacultyDtomapper {
    Faculty map(IncomingDto incomingDto);
    OutGoingDto map(Faculty faculty);
}
