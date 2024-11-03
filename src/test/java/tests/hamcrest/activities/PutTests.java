package tests.hamcrest.activities;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class PutTests {

    File putActivity = new File(ConfigurationReader.getProperty("putActivityFile")); // Ensure this file only contains necessary fields

    @DisplayName("PUT activities - Success Test")
    @Test
    public void putActivitiesSuccessTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(putActivity)
                .log().all()
                .when()
                .put("/v1/Activities/0")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("id", is(0))
                .body("title", is("Flamur"));

    }

    @DisplayName("PUT activities - Negative Test with Invalid ID")
    @Test
    public void putActivitiesNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(putActivity)
                .log().all()
                .when()
                .put("/v1/Activities/999sdjfdsfb999")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
