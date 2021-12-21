package Cart;

import Goods.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    Goods goods ;
    public ArrayList<Good> godsUser ;

    public Cart(String nameUser) {
        goods =new Goods();
        godsUser = new ArrayList<>();
        readCart(nameUser);
    }

    public synchronized void updaterCart(String guestId){
        godsUser = new ArrayList<>();
        readCart(guestId);
    }

    public synchronized void removeCartGuest(String name, String guestId) {
        HashMap<String ,ArrayList<Good>> goodsUserAll = new HashMap<>();
        ArrayList<Good> goodsGuest = new ArrayList<>();
        ArrayList<Good> goodsUser = new ArrayList<>();
        try {
            String userInfo;
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/Carts.txt")));
            while ((userInfo = inputStreamReader.readLine()) != null) {

                String[] strData = userInfo.split("\t");
                ArrayList<Good> gl = new ArrayList<>();
                for (int i = 1; i < strData.length; i++) {
                    String strId = strData[i];
                    String strQal = strData[i + 1];


                    for (Good g : goods.goods) {

                        if (g.getId() == Integer.parseInt(strId)) {
                            Good goodNew = new Good(g.getPathImg(),g.getName(),g.getCost(),g.getQuality(),g.getId());
                            goodNew.setVal(Integer.parseInt(strQal));
                            gl.add(goodNew);
                            if(strData[0].equals(name)){
                                goodsUser.add(goodNew);
                            }
                            if(strData[0].equals(guestId)){
                                goodsGuest.add(goodNew);
                            }

                        }
                    }
                    i++;
                }
                goodsUserAll.put(strData[0], gl);

            }
            inputStreamReader.close();
        } catch (Exception e) {
        }

        boolean b =true;
       for(Good p : goodsGuest){
           for(Good g : goodsUser){
               if(g.getId() == p.getId()){
                   b=false;
                   g.setVal(g.getVal()+p.getVal());
               }
           }
           if(b){
               goodsUser.add(p);
           }
           b=true;
       }

        try{
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/Carts.txt",false)));
            StringBuilder sb = new StringBuilder();
            String[] st = new String[goodsUserAll.keySet().size()];
            st = goodsUserAll.keySet().toArray(st);

            for(int i =0 ; i < goodsUserAll.keySet().size() ; i++){

                if(st[i].equals(name)){
                    sb.append(st[i]);
                    for(Good g : goodsUser){
                        if(st[i].equals(guestId) ){}else {
                            sb.append("\t" + g.getId() + "\t" + g.getVal());
                        }
                    }
                    sb.append("\n");
                }else {
                    if (!st[i].equals(guestId)) {
                        sb.append(st[i]);

                        for (Good g : goodsUserAll.get(st[i])) {

                            sb.append("\t" + g.getId() + "\t" + g.getVal());

                        }
                        sb.append("\n");
                    }
                }


            }

            output.append(String.valueOf(sb));
            output.flush();
            output.close();
        }catch (Exception e){

        }
    }

    public synchronized void readCart(String nameUser){
        try {
            String userInfo;
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/Carts.txt")));
            while ((userInfo = inputStreamReader.readLine()) != null) {

                String[] strData = userInfo.split("\t");
                if (!strData[0].equals(nameUser)) {
                    continue;
                }
                for (int i = 1; i < strData.length; i++) {
                    String strId = strData[i];
                    String strQal = strData[i + 1];

                    for (Good g : goods.goods) {
                        if (g.getId() == Integer.parseInt(strId)) {
                            Good goodNew = new Good(g.getPathImg(),g.getName(),g.getCost(),g.getQuality(),g.getId());
                            goodNew.setVal(Integer.parseInt(strQal));
                            godsUser.add(goodNew);
                        }
                    }
                    i++;
                }

            }
            inputStreamReader.close();
        } catch (Exception e) {
        }
    }
}
