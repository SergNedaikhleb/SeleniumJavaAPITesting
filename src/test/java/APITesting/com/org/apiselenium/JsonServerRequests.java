package APITesting.com.org.apiselenium;

import APITesting.com.org.classes.Posts;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class JsonServerRequests {

    // GET request

    @Test
    public void test_01() {

        Response resp = given().
                when().get("http://localhost:3000/posts");

        System.out.println("Get response is : " + resp.asString());
    }

    // POST request
    @Test
    public void test_02() {

       Response resp1 =  given().
        body(" {\"id\": \"2\", \"title\":\"dummyTitle\", \"author\": \"Vainhav\"} ").
                when().contentType(ContentType.JSON).
        post("http://localhost:3000/posts");

        System.out.println(resp1.asString());
    }

    // POST request
    @Test
    public void test_03() {

        Posts posts = new Posts();
        posts.setId("3");
        posts.setTitle("post request by object");
        posts.setAuthor("Vladimir");

       Response resp2 = given().
               when().contentType(ContentType.JSON).
               body(posts).
               post("http://localhost:3000/posts");

       System.out.println("Response : "+ resp2.asString());
    }

    @Test
    public void test_04() {

        Response resp3 =
        given().
        when().
        get("http://localhost:3000/posts/3");

        System.out.println(resp3.asString());
    }


    //PUT request
    @Test
    public void test_05() {
        Posts posts = new Posts();

        posts.setId("3");
        posts.setAuthor("updated Author name");
        posts.setTitle("updated Title name");

        Response resp4 =
        given().when().contentType(ContentType.JSON).
        body(posts).put("http://localhost:3000/posts/3");

        System.out.println("Put API response: " +resp4.asString());
    }


    //PATCH request
    @Test
    public void test_06() {

        Response resp5 =
        given().body(" {\"title\": \"updated by PUT request\" }").when().contentType(ContentType.JSON).
        patch("http://localhost:3000/posts/3");

        System.out.println("PATCH request: " +resp5.asString());
    }


    //DELETE request
    @Test
    public void test_07() {

        Response resp6 =
        given().when().
        delete("http://localhost:3000/posts/1");

        System.out.println("Deleting responce: " +resp6.asString());
    }
}
