package tests.hamcrest.Authors;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GetTestBooks {

    @DisplayName("GET books - Success Test")
    @Test
    public void getBooksSuccessTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");
        given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/v1/Books/")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);



    }


    @DisplayName("GET books - Negative Test with Invalid ID")
    @Test
    public void getBooksNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/v1/Books/999999")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
