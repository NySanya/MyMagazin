package Cart;

import Goods.*;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class updaterCart {
    Goods goods ;
    HashMap<String ,ArrayList<Good>> goodsUserAll = new HashMap<>();
    HashMap<Integer, Integer> dataActualGoods;
    public updaterCart(){
        dataActualGoods = new HashMap<>();
        goods =new Goods();
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
                        }
                    }
                    i++;
                }
                goodsUserAll.put(strData[0], gl);

            }
            inputStreamReader.close();
        } catch (Exception e) {
        }

    }

    public synchronized void update( String name, int id, int value,int replace){
        HashMap<Integer, Integer> thisUserDataActualGoods =new HashMap<>();

        try {
            String userInfo;
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/Carts.txt")));
            while ((userInfo = inputStreamReader.readLine()) != null) {

                String[] strData = userInfo.split("\t");

                for (int i = 1; i < strData.length; i++) {
                    String strId = strData[i];
                    String strQal = strData[i + 1];

                        if(strData[0].equals(name)){
                            if (thisUserDataActualGoods.containsKey(Integer.parseInt(strId))) {
                                thisUserDataActualGoods.put(Integer.parseInt(strId), thisUserDataActualGoods.get(Integer.parseInt(strId)) + Integer.parseInt(strQal));
                            } else {
                                Integer a = Integer.valueOf(strQal);
                                thisUserDataActualGoods.put(Integer.parseInt(strId), a);
                            }
                        }

                        if (dataActualGoods.containsKey(Integer.parseInt(strId))) {
                            dataActualGoods.put(Integer.parseInt(strId), dataActualGoods.get(Integer.parseInt(strId)) + Integer.parseInt(strQal));
                        } else {
                            Integer a = Integer.valueOf(strQal);
                            dataActualGoods.put(Integer.parseInt(strId), a);
                        }

                    i++;
                }

            }
            inputStreamReader.close();
        } catch (Exception e) {

        }


        boolean b = true;
        if(goodsUserAll.containsKey(name)) {
            for (Good g : goodsUserAll.get(name)) {
                if (g.getId() == id) {
                    if(replace ==0) {
                        if (0< g.getQuality()-dataActualGoods.get(id) || value < 0) {
                            g.setVal(g.getVal() + value);
                        }
                    }else {
                        if(replace> 0 && (goods.getGoodOnId(id).getQuality() - dataActualGoods.get(id)) >= replace){
                            g.setVal(replace);
                        }else if(replace> 0 && (g.getQuality()  - (dataActualGoods.get(id) - thisUserDataActualGoods.get(id))) >= replace){
                            g.setVal(replace);
                        }
                    }
                    b = false;
                }
            }
            if (b) {
                for (Good g : goods.goods) {
                    if (g.getId() == id) {
                        Good goodNew = new Good(g.getPathImg(),g.getName(),g.getCost(),g.getQuality(),g.getId());
                        goodNew.setVal(1);
                        goodsUserAll.get(name).add(goodNew);
                    }
                }
            }
        }else {
            ArrayList<Good> l = new ArrayList<>();
            goodsUserAll.put(name,l);
            for (Good g : goods.goods) {
                if (g.getId() == id) {
                    Good goodNew = new Good(g.getPathImg(),g.getName(),g.getCost(),g.getQuality(),g.getId());
                    goodNew.setVal(1);
                    goodsUserAll.get(name).add(goodNew);
                }
            }

        }

        try{
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/Carts.txt",false)));
            StringBuilder sb = new StringBuilder();
            String[] st = new String[goodsUserAll.keySet().size()];
            st = goodsUserAll.keySet().toArray(st);

            for(int i =0 ; i < goodsUserAll.keySet().size() ; i++){
                sb.append(st[i]);
                for(Good g : goodsUserAll.get(st[i])){
                    if(g.getVal()!=0) {
                       sb.append("\t" + g.getId() + "\t" + g.getVal());

                    }
                }
                sb.append("\n");

            }

            output.append(String.valueOf(sb));
            output.flush();
            output.close();
        }catch (Exception e){

        }
    }


    public synchronized void remove( String name, int id){

        try{
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/Carts.txt",false)));
            StringBuilder sb = new StringBuilder();
            String[] st = new String[goodsUserAll.keySet().size()];
            st = goodsUserAll.keySet().toArray(st);

            for(int i =0 ; i < goodsUserAll.keySet().size() ; i++){
                sb.append(st[i]);
                for(Good g : goodsUserAll.get(st[i])){
                    if(st[i].equals(name) && g.getId() == id){}else {
                        sb.append("\t" + g.getId() + "\t" + g.getVal());
                    }
                }
                sb.append("\n");

            }

            output.append(String.valueOf(sb));
            output.flush();
            output.close();
        }catch (Exception e){

        }
    }

}
