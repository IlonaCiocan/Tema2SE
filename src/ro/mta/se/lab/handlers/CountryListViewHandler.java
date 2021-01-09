package ro.mta.se.lab.handlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import ro.mta.se.lab.data.City;

import java.util.List;
import java.util.Map;

public class CountryListViewHandler implements EventHandler<MouseEvent> {

    private List<City> cities;
    private ListView<String> cityList;
    private ListView<String> caller;

    public CountryListViewHandler(List<City> cities, ListView<String> cityList, ListView<String> caller) {
        super();
        this.cities = cities;
        this.cityList = cityList;
        this.caller = caller;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        String countryCode = caller.getSelectionModel().getSelectedItem();

        ObservableList<String> citiesObsList = FXCollections.observableArrayList();

        cities.stream()
                .filter(city->city.getCountry().equals(countryCode))
                .forEach(city -> citiesObsList.add(city.getName()));

        cityList.setItems(citiesObsList);
    }
}
