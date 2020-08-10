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
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import utilities.RestAssuredExtension;

import javax.xml.crypto.Data;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Then("^I should not see the body with title as \"([^\"]*)\"$")
    public void iShouldNotSeeTheBodyWithTitleAs(String title) throws Throwable {
       assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
    }

//    @Given("^I perform authentication operation for \"([^\"]*)\" with body$")
////    public void iPerformAuthenticationOperationForWithBody(String url, DataTable table) throws Throwable {
////        Object data = table.raw();
////
////        HashMap<String, String> body = new HashMap<String, String>();
//////        body.put("email", data.get(1).get(0));
////    }
}
