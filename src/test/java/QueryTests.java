import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.mta.se.lab.openWeatherAPI.Query;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpResponse;


public class QueryTests {

    private static final int INCORRECT_ID = 999999999;

    /**
     * CAnd totul este corect, ne asteptam la statusCode 200
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void runCorrectQueryTest() throws IOException, InterruptedException {
        Query query = new Query();
        query.setId(7910918).setLang("ro").setUnits("metric");
        HttpResponse<String> response = query.runQuery();
        Assert.assertEquals(response.statusCode(),200);
    }


    /**
     * Daca id-ul e gresit, ne asteptam la statusCode 404
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void runIncorrectIDQueryTest() throws IOException, InterruptedException {
        Query query = new Query();
        query.setId(INCORRECT_ID).setLang("ro").setUnits("metric");
        HttpResponse<String> response = query.runQuery();
        Assert.assertEquals(response.statusCode(),404);
    }

    /**
     * Limba este un parametru optional, deci ne asteptam la statusCode 200
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void runNoLangQueryTest() throws IOException, InterruptedException {
        Query query = new Query();
        query.setId(7910918).setUnits("metric");
        HttpResponse<String> response = query.runQuery();
        Assert.assertEquals(response.statusCode(),200);
    }

    /**
     * Unitatile sunt un parametru optional, deci ne asteptam la statusCode 200
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void runNoUnitsTest() throws IOException, InterruptedException {
        Query query = new Query();
        query.setId(7910918).setLang("ro");
        HttpResponse<String> response = query.runQuery();
        Assert.assertEquals(response.statusCode(),200);
    }

    /**
     * A se rula cand nu suntem conectati la internet. Ne asteptam la java.net.ConnectException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test(expected = ConnectException.class)
    public void noInternetConnection() throws IOException, InterruptedException{
        Query query = new Query();
        query.setId(7910918).setLang("ro").setUnits("metric");
        HttpResponse<String> response = query.runQuery();
        Assert.assertEquals(response.statusCode(),200);
    }
}
