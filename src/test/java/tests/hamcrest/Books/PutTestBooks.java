package tests.hamcrest.Authors;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class PutTestBooks {

    File putBooks = new File(ConfigurationReader.getProperty("putBooksFile"));

    @DisplayName("PUT books - Success Test")
    @Test
    public void putBooksSuccessTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(putBooks)
                .log().all()
                .when()
                .put("/v1/Books/0")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("id", is(0))
                .body("title", is("Flamur"));

    }

    @DisplayName("PUT books - Negative Test with Invalid ID")
    @Test
    public void putBooksNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(putBooks)
                .log().all()
                .when()
                .put("/v1/Books/jggjng9")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
