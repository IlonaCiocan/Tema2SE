package ro.mta.se.lab.data;

import org.javatuples.Pair;

public class City {
    private int id;
    private String name;
    private String country;
    Pair<Double,Double> coords;

    public City(int id, String name, String country, double lat,double lon) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.coords = new Pair<>(lat,lon);
    }

    public Double getLat(){
        return coords.getValue0();
    }

    public Double getLon(){
        return coords.getValue1();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
