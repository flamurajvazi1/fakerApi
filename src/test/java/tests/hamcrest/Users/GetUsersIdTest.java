package tests.hamcrest.Users;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GetUsersIdTest {

    @DisplayName("GET users Id - Success Test")
    @Test
    public void getUsersIdSuccessTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        String responseBody = given()
                .accept(ContentType.JSON)
                .log().uri()
                .log().headers()
                .when()
                .get("/v1/Users/3")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .asPrettyString();

        System.out.println("Response Body:\n" + responseBody);
    }

    @DisplayName("GET users - Negative Test with Invalid path - Bad Request")
    @Test
    public void getUsersIdNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .log().uri()
                .when()
                .get("/v1/Users/99999fdsf9")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
