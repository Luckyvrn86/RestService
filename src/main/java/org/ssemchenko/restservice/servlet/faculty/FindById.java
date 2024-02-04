package org.ssemchenko.restservice.servlet.faculty;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ssemchenko.restservice.service.FacultyService;
import org.ssemchenko.restservice.service.impl.FacultyServiceImpl;
import org.ssemchenko.restservice.servlet.mapper.FacultyDtomapperImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/facultyFind")
public class FindById extends HttpServlet {
    private final FacultyService facultyService = FacultyServiceImpl.getInstance();
    private final FacultyDtomapperImpl mapper = new FacultyDtomapperImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        var id = Integer.valueOf(req.getParameter("id"));
        try (var writer = resp.getWriter()) {
            writer.write("<h4>");
            writer.write(facultyService.findById(id).toString());
            writer.write("</h4>");
        }
    }
}
