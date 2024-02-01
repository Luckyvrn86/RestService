package org.ssemchenko.restservice.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ssemchenko.restservice.service.FacultyService;
import org.ssemchenko.restservice.service.impl.FacultyServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/facultys")
public class FacultyServlet extends HttpServlet {
    private final FacultyService facultyService = FacultyServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var writer = resp.getWriter()) {
            writer.write("<h1>Список факультетов:</h1>");
            writer.write("<ul>");
            facultyService.findAll().forEach(facultyDto -> {
                writer.write("""
                        <li>
                            <a href="/students?facultyId=%d">%s</a>
                        >/li>
                        """.formatted(facultyDto.getId(), facultyDto.getName()));
            });
            writer.write("/<ul>");

        }
    }
}
