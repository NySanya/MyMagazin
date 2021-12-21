package Servlets;
import Cart.Cart;
import Cart.updaterCart;
import Goods.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Objects;


public class Main extends HttpServlet {

    public static int countersGuest = 0;
    HashMap<Integer, Integer> dataActualGoods;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart carts ;
        ActualGoods actualGoods= new ActualGoods();
        dataActualGoods = actualGoods.getDataActualGoods();

        Goods goods = new Goods();
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();

        if (session.getAttribute("name") == null && session.getAttribute("guestId") == null) {
            session.setAttribute("guestId", String.valueOf(countersGuest));
            countersGuest++;
        }



        out.println("<html><head><title>Main</title> <link rel=\"stylesheet\" href=\"/Magazin/style.css\"></head><body>");
        out.println("<div class = \"divContainer\">");
            out.println("<div class = \"searching\">");
                out.println("<form action=\"/Magazin/Main\" method=\"post\">");
                    if(req.getParameter("searching") != null && !(req.getParameter("searching").equals(""))){
                        out.println("<input type = \"text\" placeholder= \"Searching now\" name=\"searching\" value=\""+req.getParameter("searching")+"\" >");
                    }else {
                        out.println("<input type = \"text\" placeholder= \"Searching now\" name=\"searching\" >");
                    }
                    out.println("<button type=\"submit\"></button>");
                out.println("</form>");
            out.println("</div>");

            out.println("<div class = \"buttons\">");
                out.println("<form action=\"\" method=\"post\">");
                    if(session.getAttribute("name") == null && session.getAttribute("pass") == null) {
                        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/Login\">Login</a></button>");
                        out.println("<button type=\"submit\"><a id=\"reg\" href=\"/Magazin/Registration\">Registration</a></button>");
                    }
                    else{
                        if(session.getAttribute("work").equals("admin")) {
                            out.println("Admin : " + session.getAttribute("name") + "<br>");
                             out.println("<button type=\"submit\"><a id=\"cart\" href=\"/Magazin/Edit\">Edit</a></button>");
                            //img replace button with id ="edit"
                           // out.println("<a  href=\"/Magazin/Edit\"><img id=\"editImg\" src=\"/Magazin/File/Images/ImagesIcon/edit.png\" ></img></a>\n");

                        }else {
                            out.println(session.getAttribute("name") + "<br>");
                        }
                        out.println("<button type=\"submit\"><a id=\"reg\" href=\"/Magazin/LogoutServlet\">Out</a></button>");
                    }
                    out.println("<button type=\"submit\"><a id=\"cart\" href=\"/Magazin/Cart\">Cart</a></button>");
                //img replace button with id ="cart"
                //out.println("<a  href=\"/Magazin/Cart\"><img id=\"cartImg\" src=\"/Magazin/File/Images/ImagesIcon/cart.png\" ></img></a>");
            out.println("</form>");
            out.println("</div>");
        out.println("</div>");



        if (req.getParameter("id") != null ) {
            if (!(session.getAttribute("name") == null && session.getAttribute("pass") == null)) {
                updaterCart cart = new updaterCart();
                switch (uri) {
                   case "/Magazin/Main/loss":
                        cart.update((String) (session.getAttribute("name")), Integer.parseInt(req.getParameter("id")), -1, 0);
                        break;
                   case "/Magazin/Main/enter":
                        if (req.getParameter("counterGood") != null && !Objects.equals(req.getParameter("counterGood"), "")) {
                           cart.update((String) (session.getAttribute("name")), Integer.parseInt(req.getParameter("id")), 0, Integer.parseInt(req.getParameter("counterGood")));
                        }
                        break;
                   default:
                        cart.update((String) (session.getAttribute("name")), Integer.parseInt(req.getParameter("id")), 1, 0);
                        break;
                }
            }else {
                updaterCart cart = new updaterCart();
                switch (uri) {
                   case "/Magazin/Main/loss":
                        cart.update((String) (session.getAttribute("guestId")), Integer.parseInt(req.getParameter("id")), -1, 0);
                        break;
                   case "/Magazin/Main/enter":
                        if (req.getParameter("counterGood") != null && !Objects.equals(req.getParameter("counterGood"), "")) {
                            cart.update((String) (session.getAttribute("guestId")), Integer.parseInt(req.getParameter("id")), 0, Integer.parseInt(req.getParameter("counterGood")));
                        }
                        break;
                   default:
                        cart.update((String) (session.getAttribute("guestId")), Integer.parseInt(req.getParameter("id")), 1, 0);
                        break;
                   }

            }
        }

        actualGoods= new ActualGoods();
        dataActualGoods = actualGoods.getDataActualGoods();

        if(!(session.getAttribute("name") == null && session.getAttribute("pass") == null)){
            carts= new Cart(String.valueOf(session.getAttribute("name")));
            carts.updaterCart(String.valueOf(session.getAttribute("guestId")));
            carts.removeCartGuest(String.valueOf(session.getAttribute("name")), String.valueOf(session.getAttribute("guestId")));
            carts= new Cart(String.valueOf(session.getAttribute("name")));
        }else {
            carts= new Cart(String.valueOf(session.getAttribute("guestId")));
            carts.updaterCart(String.valueOf(session.getAttribute("guestId")));
        }


        out.println("<div class=\"goodDiv\">");

                for (Good g : goods.goods) {
                    for(Good car: carts.godsUser){
                        if(car.getId() == g.getId()){
                            g.setVal(car.getVal());
                        }
                    }

                    if (dataActualGoods.get(g.getId()) != null && dataActualGoods.get(g.getId()) >= g.getQuality()) {
                       continue;
                    }

                    if(req.getParameter("searching") != null && !(req.getParameter("searching").equals(""))) {

                        if ((g.getName().contains(req.getParameter("searching"))) || String.valueOf(g.getId()).contains(req.getParameter("searching"))) {

                        }else{
                            continue;
                        }
                    }

                    out.println("<div class=\"good\">");
                    out.println("<form method=\"post\" action=\"/Magazin/Main\" >");
                    out.println("<img id=\"imgGoods\" src=\"/Magazin/" + g.getPath() + "\"></img>");
                    out.println("<div class=\"description\">");
                    out.println("<p>Name: " + g.getName() + "</p>");
                    out.println("<p>Price: " + g.getCost() + "</p>");
                    out.println("<p>Quality: " + g.getQuality() + "</p>");
                    out.println("<p >id: " + g.getId() + "</p>");
                    out.println("</div>");
                    if(g.getVal() == 0) {
                        out.println("<button id=\"bBuy\" name= \"id\" value=\"" + g.getId() + "\">Buy</button>");
                    }
                    out.println("</form>");
                    if(g.getVal() != 0) {
                        out.println("<div class = \"adder\">");
                        out.println("<form id=\"\" method=\"post\" action=\"/Magazin/Main/loss\">");
                        out.println("<button id=\"\" name= \"id\" value=\"" + g.getId() + "\">-</button>");
                        out.println("</form>");

                        out.println("<form method=\"post\" action=\"/Magazin/Main/enter\">");
                        out.println("<input type=\"text\" id=\"iValGood\" size=\"3\" name= \"counterGood\" value=\"" + g.getVal() + "\">");
                        out.println("<input type=\"submit\" id=\"invisibleInput\" size=\"0\" name =\"id\" value=\"" + g.getId() + "\">");
                        out.println("</form>");

                        out.println("<form id=\"\" method=\"post\" action=\"/Magazin/Main/add\">");
                        out.println("<button id=\"\" name= \"id\" value=\"" + g.getId() + "\">+</button>");
                        out.println("</form>");
                        out.println("</div>");
                    }
                    out.println("</div>");
                }
        out.println("</div>");
        out.println("</body>");
    }
}
