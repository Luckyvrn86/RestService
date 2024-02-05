package org.ssemchenko.restservice.servlet.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ssemchenko.restservice.service.StudentService;
import org.ssemchenko.restservice.service.impl.StudentServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/studentDelete")
public class DeleteById extends HttpServlet {

    private StudentService studentService = StudentServiceImpl.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        var id = Integer.parseInt(req.getParameter("id"));
        if (studentService.deleteById(id)) {
            try (var writer = resp.getWriter()) {
                writer.write("<h4>Студент удален</h4>");
            }
        } else {
            try (var writer = resp.getWriter()) {
                writer.write("<h4>Студента с таким ID не существует</h4>");
            }
        }
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
