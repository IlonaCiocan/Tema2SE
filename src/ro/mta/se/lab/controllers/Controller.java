package ro.mta.se.lab.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;
import ro.mta.se.lab.Properties;
import ro.mta.se.lab.data.City;
import ro.mta.se.lab.handlers.CountryListViewHandler;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {


    private List<City> cities= new ArrayList<>(20);

    @FXML
    private ListView<String> cityList;

    @FXML
    private ListView<String> countriesList;

    public Controller(){};

    @FXML
    private void initialize() throws IOException {
        readCities();
        populateCityList();
        countriesList.setOnMouseClicked(new CountryListViewHandler(cities,cityList,countriesList));
    }

    private void readCities() throws IOException {
        String jsonData = Files.readString(Properties.INIT_FILE_PATH);

        JSONArray array = new JSONArray(jsonData);

        array.forEach(o->addCity((JSONObject)o));
    }

    private void addCity(JSONObject o) {
        int id = o.getInt("id");
        String name = o.getString("name");
        String country = o.getString("country");

        JSONObject coord = o.getJSONObject("coord");
        double lat = coord.getDouble("lat");
        double lon = coord.getDouble("lon");

        City city = new City(id,name,country,lat,lon);
        cities.add(city);
    }

    private void populateCityList(){
        ObservableList<String> countries = FXCollections.observableArrayList();
        cities.forEach((city)->{
            if (!countries.contains(city.getCountry()))
                countries.add(city.getCountry());
        });

        countriesList.setItems(countries);
    }

}
