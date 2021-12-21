package Goods;

import java.io.*;
import java.util.ArrayList;

public class Goods {
    public static int valueCounter = 0;
    public ArrayList<Good> goods = new ArrayList<>();
    public int size =0 ;

    public Goods(){ readDataGoods(); }

    private synchronized void readDataGoods(){
        try {
            String userInfo;
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/dataGoods.txt")));
            while ((userInfo = inputStreamReader.readLine()) != null) {
                String[] strData = userInfo.split("\t");
                String strPath = strData[0];
                String strName = strData[1];
                String strCost = strData[2];
                String strQuality = strData[3];
                String strId = strData[4];

                add(strPath,strName,Integer.parseInt(strCost),Integer.parseInt(strQuality),Integer.parseInt(strId));
                size++;
            }
            inputStreamReader.close();
        }catch (Exception e){}
    }

    public int getLastId(){return goods.get(goods.size()-1).getId()+valueCounter;}

    public Good getGoodOnId(int id){
        for(Good g : goods){
            if(g.getId() == id){
                return g;
            }
        }
        return new Good(null,null,0,0,0);
    }

    public synchronized void add(String path, String name, int cost, int quality, int id) {goods.add(new Good(path,name, cost, quality, id));  }



}
