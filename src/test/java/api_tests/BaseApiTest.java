package api_tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;


public class BaseApiTest {
    private static final String BASE_URL = "https://ayqa241.testrail.io/";
    private static final String USERNAME = "ayqa24@mailinator.com";
    private static final String PASSWORD = "Ayqa241!";


    @BeforeTest
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(USERNAME, PASSWORD)
                .header(HTTP.CONTENT_TYPE, ContentType.JSON);
    }
}
