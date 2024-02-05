package org.ssemchenko.restservice.servlet.faculty;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ssemchenko.restservice.service.FacultyService;
import org.ssemchenko.restservice.service.impl.FacultyServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/facultyFind")
public class FindById extends HttpServlet {
    private FacultyService facultyService = FacultyServiceImpl.getInstance();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        var id = Integer.parseInt(req.getParameter("id"));
        try (var writer = resp.getWriter()) {
            writer.write("<h4>");
            writer.write(facultyService.findById(id).toString());
            writer.write("</h4>");
        }
    }

    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
}
