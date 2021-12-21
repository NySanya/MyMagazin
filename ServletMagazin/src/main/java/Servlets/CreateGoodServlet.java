package Servlets;

import Goods.*;

import javax.servlet.ServletException;
/*import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;*/
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
import java.io.*;
//import java.nio.file.Files;

/*@WebServlet("/upload")
@MultipartConfig*/
public class CreateGoodServlet extends HttpServlet {
    Goods goods ;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        goods =new Goods();
         /*Part filePart = req.getPart("file");
         InputStream fileContent = filePart.getInputStream();
         File uploads = new File("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/Images/ImagesGoods");
        Goods.valueCounter++;

         File file = new File(uploads, goods.getLastId()+".png");
         try (InputStream input = filePart.getInputStream()) {
             Files.copy(input, file.toPath());
        }

        try {
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/dataGoods.txt")));
            StringBuilder sb = new StringBuilder();
            for (Good g : goods.goods) {
                sb.append(g.getPathImg() +"\t" + g.getName()+"\t"+ g.getCost() +"\t"+ g.getQuality()+"\t"+g.getId()+"\n");
            }
            Good g = new Good(goods.getLastId()+".png", String.valueOf(req.getParameter("name")) ,Integer.parseInt(req.getParameter("price")) ,Integer.parseInt(req.getParameter("qual")),goods.getLastId());
            sb.append(g.getPathImg() +"\t" + g.getName()+"\t"+ g.getCost() +"\t"+ g.getQuality()+"\t"+g.getId()+"\n");
            goods.add(g.getPathImg(),g.getName(),g.getCost(),g.getQuality(),g.getId());
            output.append(String.valueOf(sb));
            output.flush();
            output.close();
        } catch (Exception e) {

        }*/
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>Edit</title> <link rel=\"stylesheet\" href=\"style.css\"></head><body>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/Main\">Main</a></button>");
        out.println("<br><hr>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/EditGood\">Edit</a></button>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/RemoveGood\">Remove</a></button>");
        out.println("<button type=\"submit\"><a id=\"log\" href=\"/Magazin/CreateGood\">Create</a></button>");


        out.println("<form action=\"upload\" method=\"post\" enctype=\"multipart/form-data\">\n" +

                "  <div class=\"goodCartCreate\">\n"+
                "    <input id =\"iImg\" type=\"file\" name=\"file\" />\n" +
                "<div class=\"description\">\n"+
                "    <p>Name: <input id = \"name\" name =\"name\"  type=\"text\" size=\"10\"></p>\n"+
                "    <p>Price: <input id = \"price\" name =\"price\"  type=\"text\" size=\"6\"></p>\n"+
                "    <p>Quality:  <input id = \"qual\" name =\"qual\"  type=\"text\" size=\"3\"></p>\n" +
                "</div>"+
                "    <input id=\"iCreate\" type=\"submit\" value=\"Create\"/>\n" +
                "</div>\n"+
                "</form>\n");

        out.println("</body>");
    }
}
