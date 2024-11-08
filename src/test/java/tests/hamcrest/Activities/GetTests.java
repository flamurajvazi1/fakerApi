package tests.hamcrest.Activities;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Activity;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class GetTests {

    @DisplayName("GET activities - Hamcrest")
    @Test
    public void getActivitiesSuccessTest() {

        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        List<Activity> activities = given()
                .accept(ContentType.JSON)
                .when().get("/v1/Activities")
                .then()
                .assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .extract().as(new TypeRef<List<Activity>>() {});

        assertThat(activities.size(), greaterThan(0));

        Activity firstActivity = activities.get(0);
        assertThat(firstActivity.getId(), is(1));
        assertThat(firstActivity.getTitle(), is("Activity 1"));
        assertThat(firstActivity.isCompleted(), is(false));
    }

    @DisplayName("GET activities path different - Hamcrest")
    @Test
    public void getActivitiesPathDifferentTest() {

        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given().accept(ContentType.JSON)
                .when().get("/v1/Activitie")
                .then()
                .assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .log().all();
    }
}
