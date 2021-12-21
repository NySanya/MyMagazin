package Servlets;

import User.Users;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
       doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        HttpSession session = req.getSession();
        String nameSession = (String) session.getAttribute("name");
        String passSession = (String) session.getAttribute("pass");
        PrintWriter out = resp.getWriter();


        if ((req.getParameter("name") != null) && (req.getParameter("pass") != null)) {
            session.setAttribute("name", req.getParameter("name"));
            nameSession = (String) session.getAttribute("name");
            session.setAttribute("pass", req.getParameter("pass"));
            passSession = (String) session.getAttribute("pass");

            HashMap<String, String[]> map ;
            Users users = new Users();
            users.readUsers();
            map = users.getUsers();
            if (map.containsKey(nameSession)) {
                if (map.get(nameSession)[0].equals(passSession)) {
                    session.setAttribute("work",map.get(nameSession)[3] );
                } else {
                    nameSession = null;
                    passSession = null;
                    session.setAttribute("name", null);
                    session.setAttribute("pass", null);
                }
            } else {
                nameSession = null;
                passSession = null;
                session.setAttribute("name", null);
                session.setAttribute("pass", null);
            }
        }

        out.println("<html><head><title>Login</title> <link rel=\"stylesheet\" href=\"style.css\"></head><body>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/Main\">Main</a></button>");
        out.println("<br><hr>");
        out.println("<div class = \"loginDiv\">");
        if (nameSession == null || passSession == null) {
            session.setAttribute("name", null);
            session.setAttribute("pass", null);

            out.println("<form method=\"post\" action=\"/Magazin/Login\">");
            out.println("<p>Name </p><input id = \"name\"  name =\"name\" type=\"text\" size=\"20\" >");
            out.println("<p>Pass </p><input id = \"pass\" name =\"pass\"  type=\"password\" size=\"20\" >");
            out.println("<input id=\"bLogin\" type=\"submit\" value=\"Login\">");
            out.println("</form>");
        }else {

            out.println("<h2>Hi` " + session.getAttribute("name")+"<h2>");
            if(session.getAttribute("work").equals("admin")){
                out.println("<H2>YOU ADMIN!!!</H2>");
            }

        }
        out.println("</div>");
        out.println(" </body>");
    }
}