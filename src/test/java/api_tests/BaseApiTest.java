package api_tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;


public class BaseApiTest {
    private static final String BASE_URL = "https://ibay.testrail.io/";
    private static final String USERNAME = "ilyab.sumo@gmail.com";
    private static final String PASSWORD = "Aa12345@";


    @BeforeTest
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(USERNAME, PASSWORD)
                .header(HTTP.CONTENT_TYPE, ContentType.JSON);
    }
}
