package Controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.ToyDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.ToyServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet({"/toy.json"})
public class ToyJson extends HttpServlet {

    public ToyServiceImpl service;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        ToyDto toy = mapper.readValue(jsonStream, ToyDto.class);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Detalle de juguetes desde json</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Detalle de juguetes desde json!</h1>");
            out.println("<ul>");
            out.println("<li>Id: " + toy.id() + "</li>");
            out.println("<li>Name: " + toy.name() + "</li>");
            out.println("<li>Price: " + toy.price() + "</li>");
            out.println("<li>Amount: " + toy.amount() + "</li>");
            out.println("<li>Category: " + toy.category() + "</li>");
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
            List<ToyDto> toys = service.listAllToy();
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(toys);
            resp.setContentType("application/json");
            resp.getWriter().write(json);
    }
}