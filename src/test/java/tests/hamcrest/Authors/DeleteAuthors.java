package tests.hamcrest.Activities;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DeleteAuthors {

    @DisplayName("DELETE authors - Success Test")
    @Test
    public void deleteActivitiesSuccessTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .delete("/v1/Authors/7")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @DisplayName("DELETE authors - Negative Test with Invalid ID")
    @Test
    public void deleteActivitiesNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .delete("/v1/Authors/7ktt")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
