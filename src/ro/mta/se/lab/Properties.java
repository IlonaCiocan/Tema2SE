package ro.mta.se.lab;

import java.nio.file.Path;

public class Properties {

    private Properties(){};

    public static final String APPLICATION_TITLE = "Ilona`s Weather Widget";
    public static final int WINDOW_WIDTH = 420;
    public static final int WINDOW_HEIGHT = 300;
    public static final Path INIT_FILE_PATH = Path.of("resources","init.json");
    public static final String UI_DEFINITION_FILENAME="interface.fxml";
}
