package Servlets;

import User.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class RegistrationServlet extends HttpServlet {

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

        if ((req.getParameter("name") != null) && (!Objects.equals(req.getParameter("name"), ""))  &&
                (req.getParameter("pass") != null) && (!Objects.equals(req.getParameter("pass"), ""))&&
                (req.getParameter("birthday") != null) && (!Objects.equals(req.getParameter("birthday"), "")) &&
                (req.getParameter("sex") != null) && (!Objects.equals(req.getParameter("sex"), "")))
            {
            session.setAttribute("name", req.getParameter("name"));
            nameSession = (String) session.getAttribute("name");
            session.setAttribute("pass", req.getParameter("pass"));
            passSession = (String) session.getAttribute("pass");


            HashMap<String, String[]> map ;
            Users users = new Users();
            users.readUsers();
            map = users.getUsers();
            if (map.containsKey(nameSession)) {
                nameSession = null;
                passSession = null;
                session.setAttribute("name", null);
                session.setAttribute("pass", null);
            } else {
                String[] outs = new String[4];
                outs[0] = passSession;
                outs[1] = req.getParameter("sex");
                outs[2] = req.getParameter("birthday");
                outs[3] = "worker";
                map.put(nameSession, outs);
                users.writeUsers();
            }

        }

        out.println("<html><head><title>Login</title> <link rel=\"stylesheet\" href=\"style.css\"></head><body>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/Main\">Main</a></button>");
        out.println("<br><hr>");
        out.println("<div class = \"registrationDiv\">");
        if (nameSession == null || passSession == null) {
            session.setAttribute("name", null);
            session.setAttribute("pass", null);

            out.println("<form method=\"post\" action=\"/Magazin/Registration\">");
            out.println("<p>Name </p><input id = \"name\"  name =\"name\" type=\"text\" size=\"20\" >");
            out.println("<p>Pass </p><input id = \"pass\" name =\"pass\"  type=\"password\" size=\"20\" >");
            out.println("<p>Birthday </p><input id = \"name\" name =\"birthday\"  type=\"name\" size=\"20\" >");
            out.println("<p>Sex </p>");
            out.println("<p> <input id = \"sex\" name =\"sex\"  type=\"radio\" value=\"M\" >M</p>");
            out.println("<p> <input id = \"sex\" name =\"sex\"  type=\"radio\" value=\"W\" >W</p>");
            out.println("<input id=\"bRegistration\" type=\"submit\" value=\"Registration\">");
            out.println("</form>");
        }else {
            session.setAttribute("work", "worker");
            out.println("You successful Registration! Hi " + session.getAttribute("name"));

        }
        out.println("</div>");
        out.println(" </body>");
    }

}
