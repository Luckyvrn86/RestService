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
import org.ssemchenko.restservice.servlet.dto.StudentDto;
import org.ssemchenko.restservice.servlet.mapper.StudentDtomapper;
import org.ssemchenko.restservice.servlet.mapper.StudentDtomapperImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/studentFind")
public class FindById extends HttpServlet {
    private final StudentService studentService = StudentServiceImpl.getInstance();
    private final StudentDtomapper mapper = new StudentDtomapperImpl();
    private final FacultyService facultyService = FacultyServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        var id = Integer.valueOf(req.getParameter("id"));
        StudentDto studentDto = studentService.findById(id);
        String facultyName = facultyService.findById(studentDto.getFacultyId()).getName();
        try (var writer = resp.getWriter()) {
            writer.write("<h4>");
            writer.write(studentDto + " " + facultyName + " факультет");
            writer.write("</h4>");
        }
    }
}
