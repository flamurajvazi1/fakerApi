package tests.hamcrest.Authors;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GetTestBooksId {

    @DisplayName("GET booksId - Success Test")
    @Test
    public void getBooksIdSuccessTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");
        given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/v1/Books/1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);



    }


    @DisplayName("GET booksId - Negative Test with Invalid ID")
    @Test
    public void getBooksIdNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/v1/Books/1001")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
