package tests.hamcrest.Users;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostUsersTest {


    File postUsers = new File(ConfigurationReader.getProperty("postUsersFile"));

    @DisplayName("POST users - Hamcrest")
    @Test
    public void postUsersSuccessTest() {

        baseURI = ConfigurationReader.getProperty("fakerApiUrl");


        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(postUsers).log().all()
                .when().post("/v1/Users")
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK);
        System.out.println("File path for postBooks: " + postUsers);

    }

    @DisplayName("POST Users - Negative Test with Invalid Data")
    @Test
    public void postUsersNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body("").log().all()
                .when().post("/v1/Users")
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}
