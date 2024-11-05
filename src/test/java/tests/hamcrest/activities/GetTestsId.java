package tests.hamcrest.activities;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Activity;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GetTestsId {

    @DisplayName("GET activity by ID - Hamcrest")
    @Test
    public void getActivityByIdSuccessTest() {

        baseURI = ConfigurationReader.getProperty("fakerApiUrl");


        Activity activity = given()
                .accept(ContentType.JSON)
                .when().get("/v1/Activities/1")
                .then()
                .assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .extract().as(Activity.class);

        assertThat(activity.getId(), is(1));
        assertThat(activity.getTitle(), is("Activity 1"));
        assertThat(activity.isCompleted(), is(false));
    }

    @DisplayName("GET activities path different - Hamcrest")
    @Test
    public void getActivitiesPathDifferentTest() {

        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given().accept(ContentType.JSON)
                .when().get("/v1/Activities/shd")
                .then()
                .log().body(true)
                .assertThat().statusCode(400);

    }
}
