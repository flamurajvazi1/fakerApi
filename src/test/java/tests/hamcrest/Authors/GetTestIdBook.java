package tests.hamcrest.Authors;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GetTestIdBook {

    @DisplayName("GET authors Books Id - Success Test")
    @Test
    public void getAuthorsIdSuccessTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/v1/Authors/authors/books/7")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @DisplayName("GET authors - Negative Test with Invalid ID")
    @Test
    public void getAuthorsIdNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/v1/Authors/authors/books/70tt0999")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
