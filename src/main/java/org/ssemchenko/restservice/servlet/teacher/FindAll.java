package org.ssemchenko.restservice.servlet.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ssemchenko.restservice.service.TeacherService;
import org.ssemchenko.restservice.service.impl.TeacherServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/teachers")
public class FindAll extends HttpServlet {
    private final TeacherService teacherService = TeacherServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = resp.getWriter()) {
            writer.write("<h1>Список студентов:</h1>");
            writer.write("<ul>");
            teacherService.findAll().forEach(teacherDto -> {
                writer.write("""
                        <li>
                            %s. %s
                        </li>
                        """.formatted(teacherDto.getId(), teacherDto.getName()));
            });
            writer.write("/<ul>");
        }
    }
}
