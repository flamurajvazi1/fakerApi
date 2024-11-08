package tests.hamcrest.Users;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GetUsersTest {

    @DisplayName("GET users - Success Test")
    @Test
    public void getUsersSuccessTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");
        given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/v1/Users/")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);



    }


    @DisplayName("GET users - Negative Test with Invalid path -Bad Request")
    @Test
    public void getUsersNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/v1/Users/99999fdsf9")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
