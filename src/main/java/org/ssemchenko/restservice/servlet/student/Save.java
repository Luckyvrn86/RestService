package org.ssemchenko.restservice.servlet.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ssemchenko.restservice.service.StudentService;
import org.ssemchenko.restservice.service.impl.StudentServiceImpl;
import org.ssemchenko.restservice.servlet.dto.StudentDto;
import org.ssemchenko.restservice.servlet.mapper.StudentDtomapper;
import org.ssemchenko.restservice.servlet.mapper.StudentDtomapperImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/studentSave")
public class Save extends HttpServlet {
    private final StudentService studentService = StudentServiceImpl.getInstance();
    private final StudentDtomapper mapper = new StudentDtomapperImpl();
    private StudentDto studentDto = new StudentDto();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        studentDto.setName(req.getParameter("name"));
        studentDto.setFacultyId(Integer.valueOf(req.getParameter("fid")));
        studentDto.setId(studentService.save(mapper.map(studentDto)).getId());
        if (studentDto.getId() != 0) {
            try (var writer = resp.getWriter()) {
                writer.write(studentDto.toString());
            }
        } else try (var writer = resp.getWriter()) {
            writer.write("<h4>Добавление не удалось</h4>");
        }
    }
}
