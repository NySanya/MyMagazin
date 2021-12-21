package Servlets;

import Goods.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EditGoodServlet extends HttpServlet {
    Goods goods;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        goods = new Goods();
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Edit</title> <link rel=\"stylesheet\" href=\"style.css\"></head><body>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/Main\">Main</a></button>");
        out.println("<br><hr>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/EditGood\">Edit</a></button>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/RemoveGood\">Remove</a></button>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/CreateGood\">Create</a></button>");
        if(req.getParameter("id") != null){
            for (Good g : goods.goods) {
                if(g.getId() == Integer.parseInt(req.getParameter("id"))){
                    try{
                        reload(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), Integer.parseInt(req.getParameter("price")), Integer.parseInt(req.getParameter("qual")));
                    }catch(Exception e){}

                }
            }
        }
        out.println("<div class=\"goodDiv\">");
        for (Good g : goods.goods) {
            out.println("<div class=\"goodCart\">");
            out.println("<form method=\"GET\" action=\"/Magazin/EditGood\" >");
            out.println("<img id=\"imgGoods\" src=\"" + g.getPath() + "\"></img>");
            out.println("<div class=\"description\">");
            out.println("<p>Name: <input id = \"name\" name =\"name\"  type=\"text\" size=\"10\" value=\"" + g.getName() + "\"></p>");
            out.println("<p>Price: <input id = \"price\" name =\"price\"  type=\"text\" size=\"6\" value=\"" + g.getCost() + "\"></p>");
            out.println("<p>Quality:  <input id = \"qual\" name =\"qual\"  type=\"text\" size=\"3\" value=\"" +g.getQuality() +"\"></p>" );
            out.println("<p >id: " + g.getId() + "</p>");
            out.println("</div>");
            out.println("<button id=\"bBuy\" name= \"id\" value=\"" + g.getId() + "\">Edit</button>");
            out.println("</form>");
            out.println("</div>");
        }
        out.println("</div>");
        out.println("</body>");
    }

    private synchronized void reload(int id, String name, int price, int qual){
        for (Good g : goods.goods) {
            if(g.getId() == id){
                g.setQuality(qual);
                g.setName(name);
                g.setCost(price);
            }
        }
        try {
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/dataGoods.txt")));
            StringBuilder sb = new StringBuilder();
            for (Good g : goods.goods) {
                sb.append(g.getPathImg() +"\t" + g.getName()+"\t"+ g.getCost() +"\t"+ g.getQuality()+"\t"+g.getId()+"\n");
            }
            output.append(String.valueOf(sb));
            output.flush();
            output.close();
        } catch (Exception e) {

        }
    }
}
