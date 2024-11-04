package tests.hamcrest.authors;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class PutAuthorsTest {

    File putAuthors = new File(ConfigurationReader.getProperty("putAuthorsFile")); // Ensure this file only contains necessary fields

    @DisplayName("PUT authors - Success Test")
    @Test
    public void putAuthorsSuccessTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(putAuthors)
                .log().all()
                .when()
                .put("/v1/Authors/0")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("id", is(0))
                .body("firstName", is("flamur"));

    }

    @DisplayName("PUT authors - Negative Test with Invalid ID")
    @Test
    public void putAuthorsNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(putAuthors)
                .log().all()
                .when()
                .put("/v1/Authors/jggjng9")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
