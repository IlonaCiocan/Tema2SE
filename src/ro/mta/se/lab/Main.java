package ro.mta.se.lab;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import ro.mta.se.lab.data.City;
import ro.mta.se.lab.handlers.CountryListViewHandler;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(Properties.UI_DEFINITION_FILENAME));
        primaryStage.setTitle(Properties.APPLICATION_TITLE);
        primaryStage.setScene(new Scene(root, Properties.WINDOW_HEIGHT,Properties.WINDOW_WIDTH));

        primaryStage.setMaxHeight(Properties.WINDOW_HEIGHT);
        primaryStage.setMaxWidth(Properties.WINDOW_WIDTH);
        primaryStage.setMinHeight(Properties.WINDOW_HEIGHT);
        primaryStage.setMinWidth(Properties.WINDOW_WIDTH);

        primaryStage.show();

       // populateCityList(root);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
