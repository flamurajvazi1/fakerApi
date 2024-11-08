package tests.hamcrest.Users;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DeleteUsersTest {

    @DisplayName("DELETE users - Success Test")
    @Test
    public void deleteUsersuccessTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .delete("/v1/Users/0")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @DisplayName("DELETE users - Negative Test with Invalid ID")
    @Test
    public void deleteUsersNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .delete("/v1/Users/7kasdnt")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
