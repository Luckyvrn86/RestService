package org.ssemchenko.restservice.servlet.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ssemchenko.restservice.service.TeacherService;
import org.ssemchenko.restservice.service.impl.TeacherServiceImpl;
import org.ssemchenko.restservice.servlet.dto.TeacherDto;
import org.ssemchenko.restservice.servlet.mapper.TeacherDtomapper;
import org.ssemchenko.restservice.servlet.mapper.TeacherDtomapperImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/teacherSave")
public class Save extends HttpServlet {
    private TeacherService teacherService = TeacherServiceImpl.getInstance();
    private final TeacherDtomapper mapper = new TeacherDtomapperImpl();
    private final TeacherDto teacherDto = new TeacherDto();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        teacherDto.setName(req.getParameter("name"));
        teacherDto.setId(teacherService.save(mapper.map(teacherDto)).getId());
        if (teacherDto.getId() != 0) {
            try (var writer = resp.getWriter()) {
                writer.write(teacherDto.toString());
            }
        } else try (var writer = resp.getWriter()) {
            writer.write("<h4>Добавление не удалось</h4>");
        }
    }

    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
