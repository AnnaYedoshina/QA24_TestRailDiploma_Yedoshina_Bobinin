package controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class BaseController {
    protected static final String BASE_URL = System.getProperty("base_url", PropertyReader.getProperty("base_url"));
    protected static final String USERNAME = System.getProperty("username", PropertyReader.getProperty("username"));
    protected static final String PASSWORD = System.getProperty("password", PropertyReader.getProperty("password"));

    public BaseController() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(USERNAME, PASSWORD)
                .header(HTTP.CONTENT_TYPE, ContentType.JSON);
    }
}
