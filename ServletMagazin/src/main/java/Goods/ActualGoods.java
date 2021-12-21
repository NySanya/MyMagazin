package Goods;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ActualGoods {
    HashMap<Integer, Integer> dataActualGoods;
    public ActualGoods(){
        dataActualGoods = new HashMap<>();
        readCartOnActual();
    }

    public synchronized void readCartOnActual(){
        try {
            String userInfo;
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/Carts.txt")));
            while ((userInfo = inputStreamReader.readLine()) != null) {

                String[] strData = userInfo.split("\t");

                for (int i = 1; i < strData.length; i++) {
                    String strId = strData[i];
                    String strQal = strData[i + 1];

                    if(dataActualGoods.containsKey(Integer.parseInt(strId))){
                        dataActualGoods.put(Integer.parseInt(strId),dataActualGoods.get(Integer.parseInt(strId)) + Integer.parseInt(strQal));
                    }else{
                        Integer a = Integer.valueOf(strQal);
                        dataActualGoods.put(Integer.parseInt(strId),a);
                    }

                    i++;
                }

            }
            inputStreamReader.close();
        } catch (Exception e) {
        }
    }

    public synchronized HashMap<Integer, Integer> getDataActualGoods(){
        return dataActualGoods;
    }
}
