package ro.mta.se.lab;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import ro.mta.se.lab.data.City;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    private static Map<Integer, City> cities= new HashMap<>(20);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(Properties.UI_DEFINITION_FILENAME));
        primaryStage.setTitle(Properties.APPLICATION_TITLE);
        primaryStage.setScene(new Scene(root, Properties.WINDOW_WIDTH, Properties.WINDOW_HEIGHT));
        primaryStage.show();
    }

    private static void readCities() throws IOException {
        String jsonData = Files.readString(Properties.INIT_FILE_PATH);

        JSONArray array = new JSONArray(jsonData);

        array.forEach(o->addCity((JSONObject)o));
    }

    private static void addCity(JSONObject o) {
        int id = o.getInt("id");
        String name = o.getString("name");
        String country = o.getString("country");

        JSONObject coord = o.getJSONObject("coord");
        double lat = coord.getDouble("lat");
        double lon = coord.getDouble("lon");

        City city = new City(id,name,country,lat,lon);
        cities.put(id,city);
    }

    private static void appInit(){
        try{
            readCities();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        appInit();
        launch(args);
    }
}
