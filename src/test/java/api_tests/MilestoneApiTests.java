package api_tests;

import io.restassured.mapper.ObjectMapperType;
import models.Milestone;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class MilestoneApiTests extends BaseApiTest {
    @Test
    public void getMilestone() {
        Milestone expectedMilestone = Milestone.builder()
                .setName("Release 1.0")
                .setDescription("New features added")
                .setReferences("RF-1")
                .build();
        Milestone actualMilestone = given()
                .pathParam("milestone_id", 1)
                .log().all()
                .when()
                .get("index.php?/api/v2/get_milestone/{milestone_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().as(Milestone.class, ObjectMapperType.GSON);
        Assert.assertEquals(actualMilestone, expectedMilestone);
    }

    @Test
    public void addNewMilestone() {
        Milestone expectedMilestone = Milestone.builder()
                .setName("Release 1.0")
                .setDescription("New features added")
                .setReferences("RF-1")
                .build();
        Milestone actualMilestone = given()
                .pathParam("project_id", 7)
                .body(expectedMilestone, ObjectMapperType.GSON)
                .when()
                .post("index.php?/api/v2/add_milestone/{project_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().as(Milestone.class, ObjectMapperType.GSON);
        Assert.assertEquals(expectedMilestone, actualMilestone);
    }

    @BeforeTest
    public void addMilestone() {
        Milestone milestone = Milestone.builder()
                .setName("Release 1.0")
                .setDescription("New features added")
                .setReferences("RF-1")
                .build();
        given()
                .pathParam("project_id", 7)
                .body(milestone, ObjectMapperType.GSON)
                .when()
                .post("index.php?/api/v2/add_milestone/{project_id}")
                .then()
                .log().all();
    }

    @Test
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


    @Test
    public void deleteMilestone() {
        given()
                .pathParam("milestone_id", 5)
                .log().all()
                .when()
                .post("index.php?/api/v2/delete_milestone/{milestone_id}")
                .then()
                .log().all()
                .statusCode(SC_OK);
    }

}