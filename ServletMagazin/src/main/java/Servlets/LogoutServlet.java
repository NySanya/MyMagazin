package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();

        session.setAttribute("name",null);
        session.setAttribute("pass",null);
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/Main");
        rd.forward(req,resp);
    }
}
