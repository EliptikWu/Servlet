package Controllers;

import dtos.ToyDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.ToyRepositoryImpl.ToyRepositoryFilesImpl;
import service.ToyService;
import service.impl.ToyServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet({"/toys.xls", "/toys.html", "/toys"})
public class ToyXLS extends HttpServlet {
    public ToyRepositoryFilesImpl toy;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        ToyService service = new ToyServiceImpl();
        List<ToyDto> toy = null;
        try {
            toy = service.listAllToy();
            resp.setContentType("text/html;charset=UTF-8");
            String servletPath = req.getServletPath();
            boolean esXls = servletPath.endsWith(".xls");
            if (esXls) {
                resp.setContentType("application/vnd.ms-excel");
                resp.setHeader("Content-Disposition", "attachment;filename=toys.xls");
            }
            try (PrintWriter out = resp.getWriter()) {
                if (!esXls) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println(" <head>");
                    out.println(" <meta charset=\"UTF-8\">");
                    out.println(" <title>Student List</title>");
                    out.println(" </head>");
                    out.println(" <body>");
                    out.println(" <h1>Student List!</h1>");
                    out.println("<p><a href=\"" + req.getContextPath() + "/toys.xls" + "\">exportar a xls < / a ></p > ");
                }
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>id</th>");
                out.println("<th>name</th>");
                out.println("<th>email</th>");
                out.println("<th>semestre</th>");
                out.println("</tr>");

                toy.forEach(p ->{
                    out.println("<tr>");
                    out.println("<td>" + p.id() + "</td>");
                    out.println("<td>" + p.name() + "</td>");
                    out.println("<td>" + p.price() + "</td>");
                    out.println("<td>" + p.amount() + "</td>");
                    out.println("<td>" + p.category() + "</td>");


                    out.println("</tr>");
                });
                out.println("</table>");
                if(!esXls) {
                    out.println(" </body>");
                    out.println("</html>");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}