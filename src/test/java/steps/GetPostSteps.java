package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.core.IsNot;
import pojo.Address;
import pojo.Location;
import pojo.Posts;
import utilities.APIConstant;
import pojo.LoginBody;
import utilities.RestAssuredExtension;
import utilities.RestAssuredExtension2;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import  static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetPostSteps {

    private static ResponseOptions<Response> response;
    public static String token;

//    @Given("^I perform GET operation for \"([^\"]*)\"$")
//    public void iPerformGETOperationFor(String url) throws Throwable {
//        response = RestAssuredExtension.GetOps(url);
//    }

    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) throws Throwable {
        response = RestAssuredExtension.GetOpsWithToken(url, token);

    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String authorName) throws Throwable {

        var posts = response.getBody().as(Posts.class);
        assertThat(posts.getAuthor(), equalTo(authorName));

        //assertThat(response.getBody().jsonPath().get("author"), hasItem(authorName));
    }


    @Then("^I should see the author names$")
    public void iShouldSeeTheAuthorNames() {
        BDDStyleMethod.PerformContainsCollection();
    }

    @Then("^I should see verify GET Parameter$")
    public void iShouldSeeVerifyGETParameter() {
        BDDStyleMethod.PerformPathParameter();
        BDDStyleMethod.PerformQueryParameter();
    }

    @Given("^I perform Post operation for \"([^\"]*)\"$")
    public void iPerformPostOperationFor(String arg0) throws Throwable {
        BDDStyleMethod.PerformPostWithBodyParameter();
    }

    @Given("^I perform POST operation for \"([^\"]*)\" with body$")
    public void iPerformPOSTOperationForWithBody(String url, DataTable table) throws Throwable {
       var data = table.raw();

       HashMap<String, String> body = new HashMap<>();
       body.put("name", data.get(1).get(0));

       HashMap<String, String> pathParam = new HashMap<>();
       pathParam.put("profileNo", data.get(1).get(1));

       response = RestAssuredExtension.PostOpsWithBodyAndPathParams(url, pathParam, body);

    }

    @Then("^I should see the body has name as \"([^\"]*)\"$")
    public void iShouldSeeTheBodyHasNameAs(String name ) throws Throwable {
        assertThat(response.getBody().jsonPath().get("name"), equalTo(name));
    }

    @Given("^I ensure to Perform POST operation for \"([^\"]*)\" with body as$")
    public void iEnsureToPerformPOSTOperationForWithBodyAs(String url, DataTable table) throws Throwable {
       var data = table.raw();

       Map<String, String> body = new HashMap<>();
       body.put("id", data.get(1).get(0));
       body.put("title", data.get(1).get(1));
       body.put("author", data.get(1).get(2));

       RestAssuredExtension.PostOpsWithBody(url, body);
    }

    @And("^I Perform DELETE operation for \"([^\"]*)\"$")
    public void iPerformDELETEOperationFor(String url, DataTable table) throws Throwable {
        var data = table.raw();

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        RestAssuredExtension.DeleteOpsWithPathParams(url, pathParams);
    }

    @And("^I perform GET operation with path parameter for \"([^\"]*)\"$")
    public void iPerformGETOperationWithPathParameterFor(String url, DataTable table) throws Throwable {
      var data = table.raw();

      Map<String, String> pathParams = new HashMap<>();
      pathParams.put("postid", data.get(1).get(0));

      response = RestAssuredExtension.GetWithPathParams(url, pathParams);
    }

    @Then("^I \"([^\"]*)\" see the body with title as \"([^\"]*)\"$")
    public void iShouldNotSeeTheBodyWithTitleAs(String condition, String title) throws Throwable {
        if(condition.equalsIgnoreCase("should not")) {
            assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
        } else {
            assertThat(response.getBody().jsonPath().get("title"), is(title));
        }
    }

    @And("^I Perform PUT operation for \"([^\"]*)\"$")
    public void iPerformPUTOperationFor(String url, DataTable table) throws Throwable {
        var data = table.raw();

        Map<String, String> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        RestAssuredExtension.PutOpsWithBodyAndPathParams(url, body, pathParams);
    }

    @Given("^I perform authentication operation for \"([^\"]*)\" with body$")
    public void iPerformAuthenticationOperationForWithBody(String uri, DataTable table) throws Throwable {
        var data = table.raw();

//        HashMap<String, String> body = new HashMap<>();
//        body.put("email", data.get(1).get(0));
//        body.put("password", data.get(1).get(1));

        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(data.get(1).get(0));
        loginBody.setPassword(data.get(1).get(1));

        RestAssuredExtension2 restAssuredExtension2 = new RestAssuredExtension2(uri, APIConstant.ApiMethods.POST, null);
        token = restAssuredExtension2.Authenticate(loginBody);
//        response = RestAssuredExtension.PostOpsWithBody(url, body);
    }

    @And("^I perform GET operation with path parameter for address \"([^\"]*)\"$")
    public void iPerformGETOperationWithPathParameterForAddress(String uri, DataTable table) throws Throwable {
       var data = table.raw();
       Map<String, String> queryParams = new HashMap<>();
       queryParams.put("id", data.get(1).get(0));

//       response = RestAssuredExtension.GetWithQueryParamsWithToken(
//               url,
//               queryParams,
//               response.getBody().jsonPath().get("access_token"));

        RestAssuredExtension2 restAssuredExtension2 = new RestAssuredExtension2(uri, "GET", token);
        response = restAssuredExtension2.ExecuteAPIWithQueryParams(queryParams);

    }

    @Then("^I should see the street name as \"([^\"]*)\" for the \"([^\"]*)\" address$")
    public void iShouldSeeTheStreetNameAsForTheAddress(String streetName, String type) throws Throwable {
        var location = response.getBody().as(Location[].class);

        Address address = location[0]
                .getAddress()
                .stream()
                .filter(x -> x.getType().equalsIgnoreCase(type))
                .findFirst()
                .orElse(null);

        assertThat(address.getStreet(), equalTo(streetName));
    }

    @Then("^I should see the author name as \"([^\"]*)\" with json validation$")
    public void iShouldSeeTheAuthorNameAsWithJsonValidation(String arg0) throws Throwable {
        var responseBody = response.getBody().asString();
        assertThat(responseBody, matchesJsonSchemaInClasspath("post.json"));
//        Assert.assertEquals(responseBody, matchesJsonSchemaInClasspath("post.json"));
//        when().get(responseBody).then().assertThat().body(matchesJsonSchemaInClasspath("post.json"));
    }
}
