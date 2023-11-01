package api.adapters;

import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static utils.StringConstant.*;

public class BaseAdapter {
    private static final String TOKEN_VALUE = "84e321ba4810d5091db97b68b54ec0c385217882c14fc4caa04cad0021df990f";
    private static final String BASE_URL = "https://api.qase.io/v1";
    protected Gson converter = new Gson();

    public Response get(String url) {
        return given()
                .log().all()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .when()
                .get(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    public Response post(String url, String body) {
        return given()
                .log().all()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .header(CONTENT_TYPE, JSON)
                .body(body)
                .when()
                .post(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    public Response post(String url) {
        return given()
                .log().all()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .header(CONTENT_TYPE, JSON)
                .when()
                .post(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    public Response patch(String url, String body) {
        return given()
                .log().all()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .header(CONTENT_TYPE, JSON)
                .body(body)
                .when()
                .patch(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    public Response patch(String url) {
        return given()
                .log().all()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .header(CONTENT_TYPE, JSON)
                .when()
                .patch(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    public Response delete(String url) {
        return given()
                .log().all()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .when()
                .delete(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }
}
