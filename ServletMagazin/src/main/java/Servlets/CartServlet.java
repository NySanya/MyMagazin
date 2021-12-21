package Servlets;

import Cart.*;
import Goods.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class CartServlet extends HttpServlet {
    Goods goods ;
    HashMap<Integer, Integer> dataActualGoods;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        goods = new Goods();
        ActualGoods actualGoods= new ActualGoods();
        dataActualGoods = actualGoods.getDataActualGoods();
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        String uri =req.getRequestURI();

        out.println("<html><head><title>Cart</title> <link rel=\"stylesheet\" href=\"/Magazin/style.css\"></head><body>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/Main\">Main</a></button>");
        out.println("<br><hr>");

        if(session.getAttribute("name") != null && session.getAttribute("pass") != null ) {
            updaterCart carts = new updaterCart();

            if (req.getParameter("id") != null) {

                switch (uri) {
                    case "/Magazin/Cart/loss":
                        carts.update((String) (session.getAttribute("name")), Integer.parseInt(req.getParameter("id")), -1, 0);
                        break;
                    case "/Magazin/Cart/enter":
                        if (req.getParameter("counterGood") != null && !Objects.equals(req.getParameter("counterGood"), "")) {
                            carts.update((String) (session.getAttribute("name")), Integer.parseInt(req.getParameter("id")), 0, Integer.parseInt(req.getParameter("counterGood")));
                        }
                        break;
                    default:
                        carts.update((String) (session.getAttribute("name")), Integer.parseInt(req.getParameter("id")), 1, 0);
                        break;
                }


            }

            if (req.getParameter("id") != null && uri.equals("/Magazin/Cart")) {

                carts.remove((String) (session.getAttribute("name")), Integer.parseInt(req.getParameter("id")));
            }

            Cart cart = new Cart((String) session.getAttribute("name"));
            if (session.getAttribute("guestId") != null) {
                cart.updaterCart(String.valueOf(session.getAttribute("guestId")));
                cart.removeCartGuest(String.valueOf(session.getAttribute("name")), String.valueOf(session.getAttribute("guestId")));
                cart = new Cart((String) session.getAttribute("name"));
            }



            if(!cart.godsUser.isEmpty() ) {
                out.println("<div class=\"goodDiv\">");
                for (Good g : cart.godsUser) {
                    out.println("<div class=\"goodCart\">");
                    out.println("<form action=\"/Magazin/Cart\" method=\"GET\">");
                    out.println("<button id=\"\" name =\"id\" value=\"" + g.getId() + "\">x</button>");
                    out.println("<img id=\"imgGoods\" src=\"/Magazin/" + g.getPath() + "\"></img>");
                    out.println("<div class=\"description\">");
                    out.println("<p>Name: " + g.getName() + "</p>");
                    out.println("<p>Price: " + g.getCost() + "</p>");
                    out.println("<p>Quality: " + g.getQuality() + "</p>");
                    out.println("<p>id: " + g.getId() + "</p>");
                    out.println("</div>");
                    out.println("</form>");
                    if(g.getVal() != 0) {

                        out.println("<div class = \"adder\">");
                        out.println("<form id=\"\" method=\"GET\" action=\"/Magazin/Cart/loss\">");
                        out.println("<button id=\"\" name= \"id\" value=\"" + g.getId() + "\">-</button>");
                        out.println("</form>");


                        out.println("<form method=\"GET\" action=\"/Magazin/Cart/enter\">");
                        out.println("<input type=\"text\" id=\"iValGood\" size=\"3\" name= \"counterGood\" value=\"" + g.getVal() + "\">");
                        out.println("<input type=\"submit\" id=\"invisibleInput\" size=\"0\" name =\"id\" value=\"" + g.getId() + "\">");
                        out.println("</form>");

                        out.println("<form id=\"\" method=\"GET\" action=\"/Magazin/Cart/add\">");
                        out.println("<button id=\"\" name= \"id\" value=\"" + g.getId() + "\">+</button>");
                        out.println("</form>");
                        out.println("</div>");
                    }
                    out.println("</div>");
                }
                out.println("</div>");
            }else{
                out.println("<div class=\"emptyCartDiv\"><h2>You cart is Empty !!!</h2></div>");
            }
        }else if(session.getAttribute("guestId") != null){


            if(req.getParameter("id") != null) {
                updaterCart carts = new updaterCart();
                switch (uri) {
                    case "/Magazin/Cart/loss":
                        carts.update((String) (session.getAttribute("guestId")), Integer.parseInt(req.getParameter("id")), -1, 0);
                        break;
                    case "/Magazin/Cart/enter":
                        if (req.getParameter("counterGood") != null && !Objects.equals(req.getParameter("counterGood"), "")) {
                            carts.update((String) (session.getAttribute("guestId")), Integer.parseInt(req.getParameter("id")), 0, Integer.parseInt(req.getParameter("counterGood")));
                        }
                        break;
                    default:
                        carts.update((String) (session.getAttribute("guestId")), Integer.parseInt(req.getParameter("id")), 1, 0);
                        break;
                }
            }

            if(req.getParameter("id")!= null && uri.equals("/Magazin/Cart")){
                updaterCart cart = new updaterCart();
                cart.remove(String.valueOf(session.getAttribute("guestId")),Integer.parseInt(req.getParameter("id")) );
            }

            Cart cart = new Cart(String.valueOf(session.getAttribute("guestId")) );
            if(!cart.godsUser.isEmpty() ) {
                out.println("<div class=\"goodDiv\">");
                for (Good g : cart.godsUser) {
                    out.println("<div class=\"goodCart\">");
                    out.println("<form action=\"/Magazin/Cart\" method=\"GET\">");
                    out.println("<button id=\"\" name =\"id\" value=\"" + g.getId() + "\">x</button>");
                    out.println("<img id=\"imgGoods\" src=\"/Magazin/" + g.getPath() + "\"></img>");
                    out.println("<div class=\"description\">");
                    out.println("<p>Name: " + g.getName() + "</p>");
                    out.println("<p>Price: " + g.getCost() + "</p>");
                    out.println("<p>Quality: " + g.getQuality() + "</p>");
                    out.println("<p>id: " + g.getId() + "</p>");
                    out.println("</div>");
                    out.println("</form>");
                    if(g.getVal() != 0) {

                        out.println("<div class = \"adder\">");
                        out.println("<form id=\"\" method=\"GET\" action=\"/Magazin/Cart/loss\">");
                        out.println("<button id=\"\" name= \"id\" value=\"" + g.getId() + "\">-</button>");
                        out.println("</form>");


                        out.println("<form method=\"GET\" action=\"/Magazin/Cart/enter\">");
                        out.println("<input type=\"text\" id=\"iValGood\" size=\"3\" name= \"counterGood\" value=\"" + g.getVal() + "\">");
                        out.println("<input type=\"submit\" id=\"invisibleInput\" size=\"0\" name =\"id\" value=\"" + g.getId() + "\">");
                        out.println("</form>");

                        out.println("<form id=\"\" method=\"GET\" action=\"/Magazin/Cart/add\">");
                        out.println("<button id=\"\" name= \"id\" value=\"" + g.getId() + "\">+</button>");
                        out.println("</form>");
                        out.println("</div>");
                    }
                    out.println("</div>");
                }
                out.println("</div>");
            }else{
                out.println("<div class=\"emptyCartDiv\"><h2>You cart is Empty !!!</h2></div>");
            }
        }

        out.println("</body>");


    }
}
