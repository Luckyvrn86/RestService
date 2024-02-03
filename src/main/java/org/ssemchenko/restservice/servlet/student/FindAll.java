package org.ssemchenko.restservice.servlet.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ssemchenko.restservice.service.FacultyService;
import org.ssemchenko.restservice.service.StudentService;
import org.ssemchenko.restservice.service.impl.FacultyServiceImpl;
import org.ssemchenko.restservice.service.impl.StudentServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/students")
public class FindAll extends HttpServlet {
    private final StudentService studentService = StudentServiceImpl.getInstance();
    private final FacultyService facultyService = FacultyServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try {
            var id = Integer.valueOf(req.getParameter("facultyId"));
            try (var writer = resp.getWriter()) {
                writer.write("<h1>Список студентов:</h1>");
                writer.write("<ul>");
                studentService.findByFacultyId(id).forEach(studentDto -> {
                    String facultyName = facultyService.findById(studentDto.getFacultyId()).getName();
                    writer.write("""
                            <li>
                                %s. %s %s факультет
                            </li>
                            """.formatted(studentDto.getId(), studentDto.getName(), facultyName));
                });
                writer.write("/<ul>");
            }
        } catch (NumberFormatException e) {
            try (var writer = resp.getWriter()) {
                writer.write("<h1>Список студентов:</h1>");
                writer.write("<ul>");
                studentService.findAll().forEach(studentDto -> {
                    String facultyName = facultyService.findById(studentDto.getFacultyId()).getName();
                    writer.write("""
                            <li>
                                %s. %s %s факультет
                            </li>
                            """.formatted(studentDto.getId(), studentDto.getName(), facultyName));
                });
                writer.write("/<ul>");
            }
        }
    }
}
