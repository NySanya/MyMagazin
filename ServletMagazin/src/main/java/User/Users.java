package User;

import java.io.*;
import java.util.HashMap;

public class Users {
    HashMap<String, String[]> map ;
    public Users(){
        map = new HashMap<>();
    }

    public synchronized void readUsers(){
        try {
            String userInfo;
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/dataUser.txt")));
            while ((userInfo = inputStreamReader.readLine()) != null) {
                String[] outs = new String[4];
                String[] strData = userInfo.split("\t");
                String strName = strData[0];
                outs[0] = strData[1];
                outs[1] = strData[2];
                outs[2] = strData[3];
                outs[3] = strData[4];
                map.put(strName, outs);

            }
            inputStreamReader.close();
        }catch (Exception e){}
    }

    public synchronized void writeUsers(){
        try {
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("/home/osboxes/apache-tomcat-9.0.54/webapps/Magazin/File/dataUser.txt")));
            StringBuilder sb = new StringBuilder();
            String[] st = new String[map.keySet().size()];
            st = map.keySet().toArray(st);
            for (int i = 0; i < map.keySet().size() ; i++) {
                sb.append(st[i] + "\t");
                sb.append(map.get(st[i])[0] + "\t" +map.get(st[i])[1]+ "\t"+map.get(st[i])[2] +"\t" + map.get(st[i])[3]+"\n");

            }
            output.append(String.valueOf(sb));
            output.flush();
            output.close();
        } catch (Exception e) {}
    }

    public synchronized HashMap<String, String[]> getUsers(){ return map;}

}
