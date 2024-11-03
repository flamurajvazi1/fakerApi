package tests.hamcrest.authors;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostTest {


    File postAuthors = new File(ConfigurationReader.getProperty("postAuthorsFile"));

    @DisplayName("POST authors - Hamcrest")
    @Test
    public void postAuthorsSuccessTest() {

        baseURI = ConfigurationReader.getProperty("fakerApiUrl");


        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(postAuthors).log().all()
                .when().post("/v1/Authors")
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }

    @DisplayName("POST authors - Negative Test with Invalid Data")
    @Test
    public void postAuthorsNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body("").log().all()
                .when().post("/v1/Authors")
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}
