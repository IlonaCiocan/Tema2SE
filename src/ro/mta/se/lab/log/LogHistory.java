package ro.mta.se.lab.log;

import org.json.JSONArray;
import org.json.JSONObject;
import ro.mta.se.lab.data.Forecast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogHistory {

    private LogHistory(){};

    private static Path LOG_HISTORY_PATH= Paths.get("resources/history.json");

    public static void log(Forecast forecast){

        try{

            if(!Files.exists(LOG_HISTORY_PATH)){
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
