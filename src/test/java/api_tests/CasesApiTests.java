package api_tests;

import com.google.gson.JsonObject;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class CasesApiTests extends BaseApiTest {
    private static final int SUITE_ID = 1;
    private static final int PROJECT_ID = 7;
    private int caseId;
    private String title = "New case";

    @BeforeTest
    public void addNewTestCase() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("title", title);
        JsonPath responseBody = given()
                .body(requestBody.toString())
                .pathParam("suite_id", SUITE_ID)
                .when()
                .log().all()
                .post("index.php?/api/v2/add_case/{suite_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().body().jsonPath();
        this.caseId = responseBody.getInt("id");
    }

    @Test
    public void addTestCaseFromFile() {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/requestCaseBody.json");
        given()
                .pathParam("suite_id", SUITE_ID)
                .body(file)
                .when()
                .post("index.php?/api/v2/add_case/{suite_id}")
                .then()
                .log().all()
                .statusCode(SC_OK);

    }

    @Test
    public void addTestCase() {
        TestCase expectedTestCase = TestCase.builder().setTitle(title)
                .setEstimate("3m")
                .build();
        TestCase actualTestCase = given()
                .body(expectedTestCase, ObjectMapperType.GSON)
                .pathParam("suite_id", SUITE_ID)
                .when()
                .post("index.php?/api/v2/add_case/{suite_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().as(TestCase.class, ObjectMapperType.GSON);
        Assert.assertEquals(expectedTestCase, actualTestCase);
    }

    @Test
    public void getTestCase() {
        TestCase expectedTestCase = TestCase.builder()
                .setTitle(title)
                .setEstimate("3m")
                .build();
        TestCase actualTestCase = given()
                .log().all()
                .pathParam("case_id", this.caseId)
                .when()
                .get("index.php?/api/v2/get_case/{case_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().as(TestCase.class, ObjectMapperType.GSON);
        Assert.assertEquals(actualTestCase, expectedTestCase);
    }

    @Test
    public void updateTestCase() {
        TestCase expectedTestCase = TestCase.builder().setTitle("This is updated testcase")
                .setEstimate("3m")
                .build();
        TestCase actualTestCase = given()
                .pathParam("case_id", caseId)
                .body(expectedTestCase, ObjectMapperType.GSON)
                .when()
                .post("index.php?/api/v2/update_case/{case_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().as(TestCase.class, ObjectMapperType.GSON);
        Assert.assertEquals(expectedTestCase, actualTestCase);

    }

    @Test
    public void deleteTestCase() {
        given()
                .pathParam("case_id", caseId)
                .log().all()
                .when()
                .post("index.php?/api/v2/delete_case/{case_id}")
                .then()
                .log().all()
                .statusCode(SC_OK);
    }

    @Test
    public void getTestCases() {
        JsonPath responseBody = given()
                .pathParam("suite_id", SUITE_ID)
                .when()
                .log().all()
                .get("index.php?/api/v2/get_cases/{suite_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().body().jsonPath();
        int size = responseBody.getInt("size");
        Assert.assertEquals(responseBody.getInt("size"), size);
    }

}

