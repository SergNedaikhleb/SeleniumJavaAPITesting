package APITesting.com.org.apiselenium;

import APITesting.com.org.classes.Info;
import APITesting.com.org.classes.Information;
import APITesting.com.org.classes._Posts;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;

import static com.jayway.restassured.RestAssured.given;

public class RequestPostWithArray {

    //Complex POST
    @Test
    public void test_1() {

       Information information1 = new Information();
       information1.setEmail("test email 1");
       information1.setPhone("test phone 1");
       information1.setAddress("test address 1");

       Information information2 = new Information();
        information2.setEmail("test email 2");
        information2.setPhone("test phone 2");
        information2.setEmail("test address 2");

        _Posts posts = new _Posts();
        posts.setId("100");
        posts.setTitle("title");
        posts.setAuthor("Author");
    //    posts.setInfo(new Information[] {information1, information2});

       Response POSTresp =
        given().when().contentType(ContentType.JSON).
                body(posts).post("http://localhost;3000/posts");

        System.out.println("Response: " +POSTresp.asString());
    }

    //GET request calculate response time
    //GET
    @Test
    public void test_2() {
       Response respTime =  given().
        when().
        get("http://localhost;3000/posts");

        Long time = respTime.then().extract().time();

        System.out.println("Response time is: " +time);


        given().
                when().
                get("http://localhost;3000/posts").then().
                and().time(lessThan(800L));
    }
}
