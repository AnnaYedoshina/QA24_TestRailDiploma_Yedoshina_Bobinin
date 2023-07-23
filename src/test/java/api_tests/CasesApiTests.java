package api_tests;

import com.github.javafaker.Faker;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Section;
import models.Case;
import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class CasesApiTests extends BaseApiTest {
    private static final int SUITE_ID = 1;
    protected int caseId;
    protected String title = "Test testCase";
    protected int sectionId;
    static Faker faker = new Faker();
    @BeforeTest
    public void addNewTestCase() {
        Section section = Section.builder()
                .setName("Test section")
                .setDescription("Our test section")
                .setParentId("")
                .setSuiteId(SUITE_ID)
                .build();
        Response response = sectionController.addSection(section, projectId);
        sectionId = response.getBody().jsonPath().getInt("id");
        Case testCase = Case.builder()
                .setTitle(title)
                .setEstimate("2m")
                .setTypeId(faker.random().nextInt(1, 4))
                .setPriorityId(faker.random().nextInt(1, 4))
                .setTemplateId(faker.random().nextInt(1, 3))
                .setSectionId(sectionId)
                .build();
        Response responseCase = casesController.addTestCase(testCase, sectionId);
        caseId = responseCase.getBody().jsonPath().getInt("id");
    }
    @Test(priority = 1)
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

    @Test(priority = 2)
    public void getTestCase() {
        JsonPath responseBody = given()
                .pathParam("case_id", this.caseId)
                .when()
                .log().all()
                .get("index.php?/api/v2/get_case/{case_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().body().jsonPath();
        Assert.assertEquals(responseBody.getString("title"), title);
    }

    @Test(priority = 3)
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
    @Test(priority = 4)
    public void deleteNewTestCase() {
        Response response = casesController.deleteTestCase(caseId);
        Assert.assertEquals(response.getStatusCode(),200);

    }

}

