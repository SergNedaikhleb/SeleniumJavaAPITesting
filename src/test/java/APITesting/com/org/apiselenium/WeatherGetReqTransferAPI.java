package APITesting.com.org.apiselenium;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.jayway.restassured.RestAssured.*;


public class WeatherGetReqTransferAPI {

    @Test
    public void test_10() {
        Response resp = given().
                parameter("id", "2172797").
                parameter("appid", "b6910d714a6eblablabla761fae22").
                when().get("https://samples.openweathermap.org/data/2.5/weather");

        String reportID = resp.
                then().
                contentType(ContentType.JSON).extract().
                path("weather[0].description");

        System.out.println("weather description by ID"+ reportID);


        String longitude = String.valueOf(resp.then().contentType(ContentType.JSON).extract().path("coord.lon"));
        String latitude = String.valueOf(resp.then().contentType(ContentType.JSON).extract().path("coord.lat"));

        System.out.println("Longitude is : "+longitude);
        System.out.println("Latitude is : "+latitude);

        String reportbyCoordinates = given().
                parameter("lat", latitude).
                parameter("lon", longitude).
                parameter("appid", "b6910d714a6eblablabla761fae22").
                when().get("https://samples.openweathermap.org/data/2.5/weather").
                then().
                contentType(ContentType.JSON).
                extract().
                path("weather[0].description");


        String reportbyID = resp.
                then().
                contentType(ContentType.JSON).
                extract().
                path("weather[0].description");


        System.out.println("report by coordinates :" + reportbyCoordinates);

        Assert.assertEquals(reportbyID, reportbyCoordinates);
    }

}
