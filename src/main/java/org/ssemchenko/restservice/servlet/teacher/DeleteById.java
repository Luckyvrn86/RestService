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

@WebServlet("/teacherDelete")
public class DeleteById extends HttpServlet {
    private TeacherService teacherService = TeacherServiceImpl.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        var id = Integer.parseInt(req.getParameter("id"));
        if (teacherService.deleteById(id)) {
            try (var writer = resp.getWriter()) {
                writer.write("<h4>Преподаватель удален</h4>");
            }
        } else {
            try (var writer = resp.getWriter()) {
                writer.write("<h4>Преподавателя с таким ID не существует</h4>");
            }
        }
    }

    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
