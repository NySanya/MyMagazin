package Servlets;

import Goods.Good;
import Goods.Goods;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RemoveGoodServlet extends HttpServlet {
    Goods goods ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        goods =new Goods();
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Edit</title> <link rel=\"stylesheet\" href=\"style.css\"></head><body>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/Main\">Main</a></button>");
        out.println("<br><hr>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/EditGood\">Edit</a></button>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/RemoveGood\">Remove</a></button>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/CreateGood\">Create</a></button>");
        if(req.getParameter("id") != null){

            goods.goods.removeIf(g -> g.getId() == Integer.parseInt(req.getParameter("id")));
            try{
                remove(Integer.parseInt(req.getParameter("id")));
            }catch(Exception e){}

        }
        out.println("<div class=\"goodDiv\">");
        for (Good g : goods.goods) {
            out.println("<div class=\"goodCart\">");
            out.println("<form method=\"GET\" action=\"/Magazin/RemoveGood\" >");
            out.println("<img id=\"imgGoods\" src=\"" + g.getPath() + "\"></img>");
            out.println("<div class=\"description\">");
            out.println("<p>Name: " + g.getName() + "</p>");
            out.println("<p>Price: " + g.getCost() + "</p>");
            out.println("<p>Quality: " +g.getQuality() +"</p>" );
            out.println("<p>id: " + g.getId() + "</p>");
            out.println("</div>");
            out.println("<button id=\"bRemoved\" name= \"id\" value=\"" + g.getId() + "\">Remove</button>");
            out.println("</form>");
            out.println("</div>");
        }
        out.println("</div>");
        out.println("</body>");
    }

    private synchronized void remove( int id){
        try {
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/dataGoods.txt")));
            StringBuilder sb = new StringBuilder();
            for (Good g : goods.goods) {
                if(id != g.getId()) {
                    sb.append(g.getPathImg() + "\t" + g.getName() + "\t" + g.getCost() + "\t" + g.getQuality() + "\t" + g.getId() + "\n");
                }
            }
            output.append(String.valueOf(sb));
            output.flush();
            output.close();
        } catch (Exception e) {

        }
    }
}
