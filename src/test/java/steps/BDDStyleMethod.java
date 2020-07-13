package steps;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class BDDStyleMethod {

    public static void SimpleGETPost(String postNumber) {
        given().contentType(ContentType.JSON).
                when().get(String.format("http://localhost:3000/posts/%s", postNumber)).then().body("author", is("Alex"));
    }

    public static void PerformContainsCollection() {
        given().contentType(ContentType.JSON).
                when().get("http://localhost:3000/posts").then().body("author", containsInAnyOrder("Alex", "Tom")).statusCode(200);
    }

}
