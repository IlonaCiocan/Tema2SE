package ro.mta.se.lab.log;

import org.json.JSONArray;
import org.json.JSONObject;
import ro.mta.se.lab.data.Forecast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

public class LogHistory {

    private LogHistory(){};
    private static String LOGS_DIRECTORY_NAME = "logs";
    private static Path LOG_HISTORY_DIR_PATH = Path.of(LOGS_DIRECTORY_NAME);
    private static Path LOG_HISTORY_PATH= Paths.get(LOGS_DIRECTORY_NAME,"history.json");

    public static void log(Forecast forecast){

        try{

            if(!Files.exists(LOG_HISTORY_PATH)){
                if (!Files.exists(LOG_HISTORY_DIR_PATH)){
                    Files.createDirectory(LOG_HISTORY_DIR_PATH);
                }
                Files.createFile(LOG_HISTORY_PATH);
            }

            String fileRawContent = Files.readString(LOG_HISTORY_PATH);
            if (fileRawContent.equals("")){
                fileRawContent = "[]";
            }
            JSONArray fileParsedContent = new JSONArray(fileRawContent);
            JSONObject forecastJSON = new JSONObject(forecast);

            fileParsedContent.put(forecastJSON);

            Files.writeString(LOG_HISTORY_PATH,fileParsedContent.toString(2));

        }
        catch (IOException e){
            e.printStackTrace();
        }


    }

}
