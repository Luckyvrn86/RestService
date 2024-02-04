package org.ssemchenko.restservice.servlet.faculty;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ssemchenko.restservice.service.FacultyService;
import org.ssemchenko.restservice.service.impl.FacultyServiceImpl;
import org.ssemchenko.restservice.servlet.dto.FacultyDto;
import org.ssemchenko.restservice.servlet.mapper.FacultyDtomapperImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/facultySave")
public class Save extends HttpServlet {
    private final FacultyService facultyService = FacultyServiceImpl.getInstance();
    private final FacultyDtomapperImpl mapper = new FacultyDtomapperImpl();
    private final FacultyDto facultyDto = new FacultyDto();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        facultyDto.setName(req.getParameter("name"));
        facultyDto.setId(facultyService.save(mapper.map(facultyDto)).getId());
        if (facultyDto.getId() != 0) {
            try (var writer = resp.getWriter()) {
                writer.write(facultyDto.toString());
            }
        } else try (var writer = resp.getWriter()) {
            writer.write("<h4>Добавление не удалось</h4>");
        }
    }
}
