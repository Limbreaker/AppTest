package sample;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

class NewThread<filePath> extends Thread {
    ArrayList<String> UnWorkedList1 = new ArrayList<>();
    ArrayList<String> WorkedList1 = new ArrayList<>();

    private String name;
    NewThread(String name) {
        this.name = name;

    }


    @Override
    public void run(){ // Этот метод будет вызван при старте потока
            String url1 = "https://www.instagram.com/web/search/topsearch/?context=blended&query=";
            String url2 = "&rank_token=0.6342094475169566&include_reel=true";
            String url = url1 + name + url2;
            URL obj = null;
            try {
                obj = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection connection = null;
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("94.41.104.125",8080)); //Proxy settings

            try {
                connection = (HttpURLConnection) obj.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                connection.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }



            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String inputLine = null;
            StringBuffer response = new StringBuffer();
            while (true) {
                try {
                    if (!((inputLine = in.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response.append(inputLine);
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //End Get Request

            //JSON
            JSONParser parser = new JSONParser();
            Object obj1 = null;
            try {
                obj1 = parser.parse(response.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONObject jsonObj = (JSONObject) obj1;
            JSONArray msg = (JSONArray) jsonObj.get("users");
            boolean Flag = false;
            for (int i1 = 0; i1 < msg.size(); i1++){
                JSONObject jsonObj2 = (JSONObject) msg.get(i1);
                JSONObject jsonObj3 = (JSONObject) jsonObj2.get("user");
                String name11 = jsonObj3.get("username").toString();
                try {
                    sleep(1); // Задержка в 0.5 сек
                } catch (Exception e) {}
                if (name11.equals(name)){
                    System.out.println(name + " - Работает!");
                    WorkedList1.add(name11);
                    Flag = true;
                }
                try {
                    sleep(1); // Задержка в 0.5 сек
                } catch (Exception e) {}

            }




            }

        }

