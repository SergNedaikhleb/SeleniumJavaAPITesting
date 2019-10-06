package APITesting.com.org.apiselenium;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;


public class WeatherGetRequests {

    // Simple get request for getting weather request by City name
    // Status Code: 200
    @Test
    public void Test_01() {

        Response weatResp = when().
                get("https://samples.openweathermap.org/data/2.5/weather?q=London&appid=b6907d289e10d714a6e88b30761fae22");

        System.out.println(weatResp.getStatusCode());
        Assert.assertEquals(weatResp.getStatusCode(), 200);
    }

    // Status Code: 401
    @Test
    public void Test_02() {

        Response weatResp = when().
                get("https://samples.openweathermap.org/data/2.5/weather?q=London&appid=b6910d714a6eblablabla761fae22");

        System.out.println(weatResp.getStatusCode());
        Assert.assertEquals(weatResp.getStatusCode(), 401);

    }

    // How to use parameters with rest assured
    // Status Code: 401
    @Test
    public void Test_03() {

        Response weatResp = given().
                            param("q", "London").
                            param("appid", "b6907d289e10d714a6e88b30761fae22").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        System.out.println(weatResp.getStatusCode());
        Assert.assertEquals(weatResp.getStatusCode(), 200);

        if(weatResp.getStatusCode()==200) {
            System.out.println("API is working fine");
        }else{
            System.out.println("API is NOT working fine");
        }

    }

    //Assert our testcase in Rest assured api
    @Test
    public void Test_04() {

                given().
                param("q", "London").
                param("appid", "b6907d289e10d714a6e88b30761fae22").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather").
                        then().
                        assertThat().statusCode(200);
    }

    @Test
    public void Test_05() {

        Response respo = given().
                param("q", "London").
                param("appid", "b6907d289e10d714a6e88b30761fae22").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        System.out.println(respo.asString());
    }

    @Test
    public void Test_06() {

        Response resp = given().
                        parameter("zip", "201010,in").
                        param("appid", "b6907d289e10d714a6e88b30761fae22").
                        when().get("https://samples.openweathermap.org/data/2.5/weather");

        Assert.assertEquals(resp.getStatusCode(), 200);

        System.out.println(resp.asString());
    }


    // extract actual result from api's
    @Test
    public void Test_07() {

         String weatherReport = given().
                parameter("id", "2172797").
                parameter("appid", "b6907d289e10d714a6e88b30761fae22").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather").
                then().
                contentType(ContentType.JSON).
                extract().
                path("$.weather[0].description");

        System.out.println("The weather report : "+ weatherReport);
    }

    @Test
    public void Test_08() {

        Response respDescription = given().
                parameter("id", "2172797").
                parameter("appid", "b6907d289e10d714a6e88b30761fae22").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        String actualWeatherReport = respDescription.
                then().
                contentType(ContentType.JSON).extract().path("$.weather[0].description");

        String expectedWeatherReport = null;

        if(actualWeatherReport.equalsIgnoreCase(expectedWeatherReport)){
            System.out.println("Testcase PASSED");
        }else{
            System.out.println("Testcase FAILED");
        }
    }

}
