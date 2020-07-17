package steps;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.StringContains.containsString;

public class BDDStyleMethod {

    public static void SimpleGETPost(String postNumber) {
        given().contentType(ContentType.JSON).
                when().get(String.format("http://localhost:3000/posts/%s", postNumber)).then().body("author", is("Alex"));
    }

    public static void PerformContainsCollection() {
        given().contentType(ContentType.JSON).
                when().get("http://localhost:3000/posts").then().body("author", containsInAnyOrder("Alex", "Tom")).statusCode(200);
    }

    public static void PerformPathParameter() {
        given()
                .contentType(ContentType.JSON)
                .with()
                .pathParam("post", "1")
                .when()
                .get("http://localhost:3000/posts/{post}")
                .then()
                .body("author", containsString("Alex"));
    }

    public static void PerformQueryParameter() {
        given()
                .contentType(ContentType.JSON)
                .with()
                .pathParam("id", 1)
                .when()
                .get("http://localhost:3000/posts/")
                .then()
                .body("author", containsString("Alex"));
    }

}
