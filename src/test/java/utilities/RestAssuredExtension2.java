package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredExtension2 {

    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String method;
    private String url;

    /**
     * RestAssuredExtension2 constructor to pass the initial setting for the following method
     * @param uri
     * @param method
     * @param token
     */
    public RestAssuredExtension2(String uri, String method, String token) {

        this.url = "http://localhost:3000" + uri;
        this.method = method;

        if(token !=null)
            builder.addHeader("Authorization", "Bearer " +token);
    }

    /**
     * ExecuteAPI to execute the API for GET/POST/DELETE
     * @return ResponseOptions<Response>
     */
    private ResponseOptions<Response> ExecuteAPI() {
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpecification);

        if(this.method.equalsIgnoreCase(APIConstant.ApiMethods.POST))
            return request.post(this.url);
        else if(this.method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE))
            return request.delete(this.url);
        else if(this.method.equalsIgnoreCase(APIConstant.ApiMethods.GET))
            return request.get(this.url);
        return null;

    }

    /**
     * Authenticate to get the token
     * @param body
     * @return string token
     */
    public String Authenticate(Object body) {
        builder.setBody(body);
        return ExecuteAPI().getBody().jsonPath().get("access_token");
    }

    /**
     * Executing API with query Params being passed as the input of it
     * @param queryPath
     * @return Response
     */
    public ResponseOptions<Response> ExecuteAPIWithQueryParams(Map<String, String> queryPath) {
        builder.addQueryParams(queryPath);
        return ExecuteAPI();
    }

    /**
     * ExecuteWithPathParams
     * @param pathParams
     * @return
     */
    public ResponseOptions<Response> ExecuteAPIWithPathParams(Map<String, String> pathParams) {
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }

    /**
     * ExecuteWithBodyAndPathParams
     * @param pathParams
     * @param body
     * @return
     */
    public ResponseOptions<Response> ExecuteAPIWithPathParamsAndBody(Map<String, String> pathParams,
                                                             Map<String, String> body) {
        builder.setBody(body);
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }
}
