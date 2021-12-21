package Servlets;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Edit</title> <link rel=\"stylesheet\" href=\"style.css\"></head><body>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/Main\">Main</a></button>");
        out.println("<br><hr>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/EditGood\">Edit</a></button>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/RemoveGood\">Remove</a></button>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/CreateGood\">Create</a></button>");
    }




}
