package tests.hamcrest.Activities;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostTests {

    File addActivity = new File(ConfigurationReader.getProperty("addActivityFile"));

    @DisplayName("POST activities - Hamcrest")
    @Test
    public void postActivitiesSuccessTest() {

        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(addActivity).log().all()
                .when().post("/v1/Activities")
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }

    @DisplayName("POST activities - Negative Test with Empty Body")
    @Test
    public void postActivitiesNegativeTest() {

        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body("").log().all()
                .when().post("/v1/Activities")
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
