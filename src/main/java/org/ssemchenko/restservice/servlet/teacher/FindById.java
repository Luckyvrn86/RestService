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

@WebServlet("/teacherFind")
public class FindById extends HttpServlet {
    private final TeacherService teacherService = TeacherServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        var id = Integer.valueOf(req.getParameter("id"));
        TeacherDto teacherDto = teacherService.findById(id);
        try (var writer = resp.getWriter()) {
            writer.write("<h4>");
            writer.write(teacherDto.toString());
            writer.write("</h4>");
        }
    }
}
