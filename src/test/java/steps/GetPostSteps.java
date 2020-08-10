package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.Matcher;
import org.junit.Assert;
import utilities.RestAssuredExtension;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetPostSteps {

    private static ResponseOptions<Response> response;

    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) throws Throwable {
        response = RestAssuredExtension.GetOps(url);
    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String authorName) throws Throwable {
        assertThat(response.getBody().jsonPath().get("author"), hasItem(authorName));
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

//    @Given("^I perform authentication operation for \"([^\"]*)\" with body$")
////    public void iPerformAuthenticationOperationForWithBody(String url, DataTable table) throws Throwable {
////        Object data = table.raw();
////
////        HashMap<String, String> body = new HashMap<String, String>();
//////        body.put("email", data.get(1).get(0));
////    }
}
