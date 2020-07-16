package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class App1Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button12;

    @FXML
    private Button button11;

    @FXML
    private ListView listview;

    @FXML
    void initialize() {
        button12.setOnAction(actionEvent -> {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("C:\\Users\\Admin\\Desktop\\"));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
            File selectFile = fc.showOpenDialog(null);


            if(selectFile != null){
                listview.getItems().add(selectFile.getAbsoluteFile());
            }else{
                System.out.println("fsfs");
            }
        });

    }
}
