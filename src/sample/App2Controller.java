package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ResourceBundle;

public class App2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button21;

    @FXML
    private TextField textfield1;

    @FXML
    private TextField textfield2;

    @FXML
    void initialize() {


       button21.setOnAction(actionEvent -> {
           String textField1 = textfield1.getText().trim();
           if(!textField1.equals("")){
               textfield2.setText("");
               System.out.println("fsafsf");
               String url1 = "https://www.instagram.com/web/search/topsearch/?context=blended&query=";
               String url2 = "&rank_token=0.6342094475169566&include_reel=true";
               String url = url1 + textField1 + url2;
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
               boolean flag = true;
               for (int i1 = 0; i1 < msg.size(); i1++){
                   JSONObject jsonObj2 = (JSONObject) msg.get(i1);
                   JSONObject jsonObj3 = (JSONObject) jsonObj2.get("user");
                   String name11 = jsonObj3.get("username").toString();

                   if (name11.equals(textField1)){
                       System.out.println(textField1 + " - Работает!");
                       textfield2.setText(textField1 + " - Работает!");
                       flag = false;
                   }

               }
               if (flag==true){
                   textfield2.setText(textField1 + " - Не Работает");
               }
           }
           else{
               textfield2.setText("");
               System.out.println("Пустое поле");
           }
       });

    }
}
