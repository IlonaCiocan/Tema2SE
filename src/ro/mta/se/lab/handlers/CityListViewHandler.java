package ro.mta.se.lab.handlers;

import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;
import ro.mta.se.lab.data.City;
import ro.mta.se.lab.data.Forecast;
import ro.mta.se.lab.log.LogHistory;
import ro.mta.se.lab.openWeatherAPI.Query;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

public class CityListViewHandler implements EventHandler<MouseEvent> {

    private List<City> cities;
    private TableView<Forecast> weatherTable;
    private ListView<String> caller;

    public CityListViewHandler(List<City> cities, TableView<Forecast> weatherTable, ListView<String> caller) {
        this.cities = cities;
        this.weatherTable = weatherTable;
        this.caller = caller;
    }

    private Forecast createForecast(JSONObject jsonObject){

        JSONObject main = jsonObject.getJSONObject("main");
        JSONObject wind = jsonObject.getJSONObject("wind");
        JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
        JSONObject rain = jsonObject.optJSONObject("rain");


        double prec = 0;

        if (rain != null){
            prec = rain.getDouble("1h");
        }

        return new Forecast(main.optDouble("temp")
                ,wind.getDouble("speed")
                ,weather.getString("description")
                ,main.getDouble("humidity")
                ,main.getDouble("pressure")
                ,prec
                );
    }

    private void populateTable(Forecast forecast){
        weatherTable.getItems().clear();
        weatherTable.getItems().add(forecast);

        LogHistory.log(forecast);

    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        String cityName = caller.getSelectionModel().getSelectedItem();

        int cityID = cities.stream().filter(city -> city.getName().equals(cityName)).findFirst().get().getId();

        Query query = new Query();
        query.setId(cityID).setLang("ro").setUnits("metric");
        try {
            HttpResponse<String> response = query.runQuery();

            if (response.statusCode()==200){
                JSONObject obj = new JSONObject(response.body());
                Forecast forecast = createForecast(obj);
                populateTable(forecast);
            }
            else {
                throw new IOException("status code not 200");
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
