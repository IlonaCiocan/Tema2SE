package ro.mta.se.lab.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import org.json.JSONArray;
import org.json.JSONObject;
import ro.mta.se.lab.Properties;
import ro.mta.se.lab.data.City;
import ro.mta.se.lab.data.Forecast;
import ro.mta.se.lab.handlers.CityListViewHandler;
import ro.mta.se.lab.handlers.CountryListViewHandler;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {


    private List<City> cities= new ArrayList<>(20);

    @FXML
    private ListView<String> cityList;

    @FXML
    private ListView<String> countriesList;

    @FXML
    private TableView<Forecast> weatherDataTable;
    public Controller(){};

    @FXML
    private void initialize() throws IOException {
        readCities();
        populateCityList();
        countriesList.setOnMouseClicked(new CountryListViewHandler(cities,cityList,countriesList));
        cityList.setOnMouseClicked(new CityListViewHandler(cities,weatherDataTable,cityList));
    }

    private void readCities() throws IOException {

        InputStream is= getClass().getClassLoader()
                .getResourceAsStream(Properties.INIT_FILENAME);

        assert is != null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        List<String> lines = reader.lines().collect(Collectors.toList());

        String jsonData = String.join("\n",lines);

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
