package api_tests;

import com.google.gson.JsonObject;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Milestone;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class MilestoneApiTests extends BaseApiTest {
    private final static String NAME = "Release 1.0";
    private int milestoneId;

    @BeforeTest
    public void addNewMilestone() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", NAME);
        JsonPath responseBody = given()
                .body(requestBody.toString())
                .pathParam("project_id", projectId)
                .when()
                .log().all()
                .post("index.php?/api/v2/add_milestone/{project_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().body().jsonPath();
        this.milestoneId = responseBody.getInt("id");

    }

    @Test(priority = 1)
    public void getMilestone() {
        JsonPath responseBody = given()
                .pathParam("milestone_id", milestoneId)
                .when()
                .log().all()
                .get("index.php?/api/v2/get_milestone/{milestone_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().body().jsonPath();
        Assert.assertEquals(responseBody.getString("name"), NAME);
    }

    @Test(priority = 2)
    public void addMilestone() {
        Milestone expectedMilestone = Milestone.builder()
                .setName("Release 1.0")
                .setDescription("New features added")
                .setReferences("RF-1")
                .build();
        Milestone actualMilestone = given()
                .pathParam("project_id", projectId)
                .body(expectedMilestone, ObjectMapperType.GSON)
                .when()
                .post("index.php?/api/v2/add_milestone/{project_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().as(Milestone.class, ObjectMapperType.GSON);
        Assert.assertEquals(expectedMilestone, actualMilestone);
    }

    @Test(priority = 3)
    public void updateMilestone() {
        Milestone expectedMilestone = Milestone.builder()
                .setName("Release 1.1")
                .setDescription("New features added")
                .setReferences("RF-2")
                .build();
        Milestone actualMilestone = given()
                .pathParam("milestone_id", 1)
                .body(expectedMilestone, ObjectMapperType.GSON)
                .when()
                .post("index.php?/api/v2/update_milestone/{milestone_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().as(Milestone.class, ObjectMapperType.GSON);
        Assert.assertEquals(expectedMilestone, actualMilestone);

    }


    @Test(priority = 4)
    public void deleteMilestone() {
        Response response = milestoneController.deleteMilestone(milestoneId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}