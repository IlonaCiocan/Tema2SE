package ro.mta.se.lab.openWeatherAPI;

import com.sun.javafx.print.Units;
import ro.mta.se.lab.data.Forecast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Query {


    private static final String REQUEST_QUERY_ROOT = "https://api.openweathermap.org/data/2.5/weather?";
    private static final String API_KEY = "708bb81cb01c006fe78f7451329699d4";
    private static final List<String> UNITS = new ArrayList<>(Arrays.asList("standard","metric","imperial"));
    private static final HttpClient requester = HttpClient.newHttpClient();

    private String lang = null;
    private String units = null;
    private String id = null;

    public Query(){}

    public Query setLang(String lang){
        if (lang != null){
            this.lang = lang;
        }
        return this;
    }

    public Query setUnits(String units){
        if (UNITS.contains(units.toLowerCase())){
            this.units = units;
        }
        return this;
    }

    public Query setId(String id){
        this.id = id;
        return this;
    }

    public Query setId(Integer id){
        this.id = id.toString();
        return this;
    }

    private String buildQuery(){
        StringBuilder queryBuilder = new StringBuilder(REQUEST_QUERY_ROOT);

        queryBuilder.append("appid=").append(API_KEY);

        if (lang != null){
            queryBuilder.append("&lang=").append(lang);
        }
        if (units != null){
            queryBuilder.append("&units=").append(units);
        }
        if (id != null){
            queryBuilder.append("&id=").append(id);
        }

        return queryBuilder.toString();
    }

    private HttpRequest buildRequest(String query){
        return HttpRequest.newBuilder(URI.create(query))
                .header("accept","application/json")
                .GET()
                .build();
    }

    public HttpResponse<String> runQuery() throws IOException, InterruptedException {

        String query = buildQuery();

        HttpRequest request = buildRequest(query);

        HttpResponse<String> response = requester.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

}
